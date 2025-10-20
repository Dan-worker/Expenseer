package com.dprog.transaction.repository

import com.dprog.transaction.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getAllTransactions(): Flow<List<Transaction>>

    fun getTransactionsByDateRange(
        startDate: Long,
        endDate: Long,
    ): Flow<List<Transaction>>

    suspend fun getTransactionById(id: String): Transaction?

    suspend fun addTransaction(transaction: Transaction)

    suspend fun updateTransaction(transaction: Transaction)

    suspend fun deleteTransaction(transaction: Transaction)

    fun getTotalIncome(): Flow<Double>

    fun getTotalExpense(): Flow<Double>
}
