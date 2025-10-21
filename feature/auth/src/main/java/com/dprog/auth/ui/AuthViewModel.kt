package com.dprog.auth.ui

import android.app.Activity
import android.util.Log
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.GetCredentialProviderConfigurationException
import androidx.credentials.exceptions.NoCredentialException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dprog.auth.AuthService
import com.dprog.auth.AuthState
import com.dprog.auth.google.GoogleCredentialInteractor
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import java.io.IOException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface AuthUiState {
    data object Idle : AuthUiState

    data object Loading : AuthUiState

    data class Ready(
        val isSignedIn: Boolean,
    ) : AuthUiState

    data class Error(
        val message: String,
    ) : AuthUiState
}

class AuthViewModel(
    private val authService: AuthService,
    private val google: GoogleCredentialInteractor,
) : ViewModel() {
    private val _ui = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val ui: StateFlow<AuthUiState> = _ui.asStateFlow()

    init {
        viewModelScope.launch {
            authService.authState.collect { s ->
                _ui.value =
                    when (s) {
                        AuthState.Unauthenticated -> AuthUiState.Ready(false)
                        AuthState.Loading -> AuthUiState.Loading
                        is AuthState.Authenticated -> AuthUiState.Ready(true)
                        is AuthState.Error -> AuthUiState.Error(s.throwable.message ?: "Error")
                    }
            }
        }
    }

    fun signIn(activity: Activity) {
        viewModelScope.launch {
            _ui.value = AuthUiState.Loading
            try {
                val idToken = google.getGoogleIdToken(activity)
                authService.signInWithGoogleIdToken(idToken)
            } catch (e: GetCredentialCancellationException) {
                Log.d(TAG, "User cancelled Credential flow", e)
                _ui.value = AuthUiState.Ready(false)
            } catch (e: NoCredentialException) {
                Log.w(TAG, "NoCredentialException while getting Google credentials", e)
                _ui.value = AuthUiState.Error("No Google account available on device.")
            } catch (e: GetCredentialProviderConfigurationException) {
                Log.w(TAG, "Provider configuration issue (Play Services?)", e)
                _ui.value = AuthUiState.Error("Google Play Services needs update/enable to sign in.")
            } catch (e: GetCredentialException) {
                Log.w(TAG, "Generic Credential Manager error", e)
                _ui.value = AuthUiState.Error("Couldn’t get Google credentials. Try again.")
            } catch (e: GoogleIdTokenParsingException) {
                Log.w(TAG, "Invalid Google ID token", e)
                _ui.value = AuthUiState.Error("Invalid Google ID token.")
            } catch (e: IOException) {
                Log.w(TAG, "I/O error during sign-in", e)
                _ui.value = AuthUiState.Error(e.message ?: "I/O error. Please try again.")
            }
        }
    }

    fun signOut() {
        // FirebaseAuth.signOut() is non-throwing; no catch needed.
        viewModelScope.launch { authService.signOut() }
    }

    private companion object {
        const val TAG = "AuthViewModel"
    }
}
