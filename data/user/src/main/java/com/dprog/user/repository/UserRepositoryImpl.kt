package com.dprog.user.repository

import com.dprog.database.dao.UserDao
import com.dprog.database.entity.UserEntity
import com.dprog.user.model.User

class UserRepositoryImpl(
    private val dao: UserDao,
) : UserRepository {
    override suspend fun getUserById(userId: String): User? = dao.getUserById(userId)?.toDomain()

    override suspend fun getUserByEmail(email: String): User? = dao.getUserByEmail(email)?.toDomain()

    override suspend fun createUser(user: User) {
        dao.insertUser(user.toEntity())
    }

    override suspend fun updateUser(user: User) {
        dao.updateUser(user.toEntity())
    }

    private fun UserEntity.toDomain() =
        User(
            id = id,
            name = name,
            email = email,
            createdAt = createdAt,
        )

    private fun User.toEntity() =
        UserEntity(
            id = id,
            name = name,
            email = email,
            createdAt = createdAt,
        )
}
