package com.dprog.transaction.model

data class Transaction(
    val id: String,
    val type: TransactionType,
    val amount: Double,
    val categoryId: String,
    val categoryName: String,
    val date: Long,
    val note: String,
    val createdAt: Long,
)
