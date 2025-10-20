package com.dprog.database.dao

import androidx.room.*
import com.dprog.database.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    suspend fun getUserById(userId: String): UserEntity?

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(entity: UserEntity)

    @Update
    suspend fun updateUser(entity: UserEntity)

    @Delete
    suspend fun deleteUser(entity: UserEntity)
}
