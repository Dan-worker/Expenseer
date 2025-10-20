package com.dprog.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "transactions",
    indices = [
        Index("date"),
        Index("type"),
        Index("categoryId"),
        Index("createdAt"),
    ],
)
data class TransactionEntity(
    @PrimaryKey val id: String,
    val type: String, // "income" | "expense"
    val amount: Double,
    val categoryId: String,
    val categoryName: String,
    val date: Long, // epoch millis
    val note: String,
    val createdAt: Long, // epoch millis
)
