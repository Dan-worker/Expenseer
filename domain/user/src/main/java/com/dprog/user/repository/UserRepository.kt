package com.dprog.user.repository

import com.dprog.user.model.User

interface UserRepository {
    suspend fun getUserById(userId: String): User?

    suspend fun getUserByEmail(email: String): User?

    suspend fun createUser(user: User)

    suspend fun updateUser(user: User)
}
