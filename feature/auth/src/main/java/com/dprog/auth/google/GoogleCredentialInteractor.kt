package com.dprog.auth.google

import android.app.Activity
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL

class GoogleCredentialInteractor(
    private val credentialManagerFactory: (Activity) -> CredentialManager,
    private val webClientIdProvider: (Activity) -> String,
) {
    suspend fun getGoogleIdToken(activity: Activity): String {
        val credentialManager = credentialManagerFactory(activity)
        val googleIdOption =
            GetGoogleIdOption
                .Builder()
                .setServerClientId(webClientIdProvider(activity))
                .setFilterByAuthorizedAccounts(false)
                .build()

        val request =
            GetCredentialRequest
                .Builder()
                .addCredentialOption(googleIdOption)
                .build()

        val credential: Credential = credentialManager.getCredential(activity, request).credential
        if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            val google = GoogleIdTokenCredential.createFrom(credential.data)
            return google.idToken
        }
        error("Unsupported credential type")
    }
}
