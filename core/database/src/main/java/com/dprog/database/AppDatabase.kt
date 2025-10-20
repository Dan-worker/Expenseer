package com.dprog.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dprog.database.dao.CategoryDao
import com.dprog.database.dao.TransactionDao
import com.dprog.database.dao.UserDao
import com.dprog.database.entity.CategoryEntity
import com.dprog.database.entity.TransactionEntity
import com.dprog.database.entity.UserEntity

@Database(
    entities = [
        TransactionEntity::class,
        CategoryEntity::class,
        UserEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    abstract fun categoryDao(): CategoryDao

    abstract fun userDao(): UserDao
}
