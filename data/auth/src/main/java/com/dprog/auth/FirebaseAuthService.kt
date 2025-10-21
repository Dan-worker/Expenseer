package com.dprog.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirebaseAuthService(
    private val firebaseAuth: FirebaseAuth,
) : AuthService {
    override val authState: Flow<AuthState> =
        callbackFlow {
            trySend(firebaseAuth.currentUser?.toAuthState() ?: AuthState.Unauthenticated)
            val listener =
                FirebaseAuth.AuthStateListener { fa ->
                    trySend(fa.currentUser?.toAuthState() ?: AuthState.Unauthenticated)
                }
            firebaseAuth.addAuthStateListener(listener)
            awaitClose { firebaseAuth.removeAuthStateListener(listener) }
        }

    override suspend fun signInWithGoogleIdToken(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential).await()
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }

    private fun FirebaseUser.toAuthState() =
        AuthState.Authenticated(
            uid = uid,
            displayName = displayName,
            email = email,
            photoUrl = photoUrl?.toString(),
        )
}
