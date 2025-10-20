package com.dprog.user.usecase

import com.dprog.user.model.User
import com.dprog.user.repository.UserRepository
import java.io.IOException

class LoginUserUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(email: String): Result<User> =
        try {
            val user = repository.getUserByEmail(email)
            if (user != null) {
                Result.success(user)
            } else {
                Result.failure(NoSuchElementException("User not found"))
            }
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: NoSuchElementException) {
            Result.failure(e)
        }
}
