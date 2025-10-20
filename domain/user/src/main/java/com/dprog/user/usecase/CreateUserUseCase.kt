package com.dprog.user.usecase

import com.dprog.user.model.User
import com.dprog.user.repository.UserRepository
import java.io.IOException
import java.util.UUID

class CreateUserUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(
        name: String,
        email: String,
    ): Result<User> {
        return try {
            // Check if user already exists
            val existingUser = repository.getUserByEmail(email)
            if (existingUser != null) {
                return Result.failure(IllegalStateException("User with this email already exists"))
            }

            val user =
                User(
                    id = UUID.randomUUID().toString(),
                    name = name,
                    email = email,
                    createdAt = System.currentTimeMillis(),
                )
            repository.createUser(user)
            Result.success(user)
        } catch (e: IOException) {
            // Network or DB access error
            Result.failure(e)
        } catch (e: IllegalStateException) {
            // Already handled above
            Result.failure(e)
        }
    }
}
