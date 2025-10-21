package com.dprog.auth

sealed interface AuthState {
    data object Unauthenticated : AuthState

    data object Loading : AuthState

    data class Authenticated(
        val uid: String,
        val displayName: String?,
        val email: String?,
        val photoUrl: String?,
    ) : AuthState

    data class Error(
        val throwable: Throwable,
    ) : AuthState
}
