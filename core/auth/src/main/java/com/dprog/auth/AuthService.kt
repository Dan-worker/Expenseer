package com.dprog.auth

import kotlinx.coroutines.flow.Flow

interface AuthService {
    val authState: Flow<AuthState>

    suspend fun signInWithGoogleIdToken(idToken: String)

    suspend fun signOut()
}
