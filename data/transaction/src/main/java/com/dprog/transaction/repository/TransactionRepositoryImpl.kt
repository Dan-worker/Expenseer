package com.dprog.transaction.repository

import com.dprog.database.dao.TransactionDao
import com.dprog.database.entity.TransactionEntity
import com.dprog.transaction.model.Transaction
import com.dprog.transaction.model.TransactionType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionRepositoryImpl(
    private val dao: TransactionDao,
) : TransactionRepository {
    override fun getAllTransactions(): Flow<List<Transaction>> =
        dao.getAllTransactions().map { list -> list.map { it.toDomain() } }

    override fun getTransactionsByDateRange(
        startDate: Long,
        endDate: Long,
    ): Flow<List<Transaction>> =
        dao.getTransactionsByDateRange(startDate, endDate).map { list -> list.map { it.toDomain() } }

    override suspend fun getTransactionById(id: String): Transaction? = dao.getTransactionById(id)?.toDomain()

    override suspend fun addTransaction(transaction: Transaction) {
        dao.insertTransaction(transaction.toEntity())
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        dao.updateTransaction(transaction.toEntity())
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        dao.deleteTransaction(transaction.toEntity())
    }

    override fun getTotalIncome(): Flow<Double> = dao.getTotalIncome().map { it ?: 0.0 }

    override fun getTotalExpense(): Flow<Double> = dao.getTotalExpense().map { it ?: 0.0 }

    private fun TransactionEntity.toDomain() =
        Transaction(
            id = id,
            type = TransactionType.fromString(type),
            amount = amount,
            categoryId = categoryId,
            categoryName = categoryName,
            date = date,
            note = note,
            createdAt = createdAt,
        )

    private fun Transaction.toEntity() =
        TransactionEntity(
            id = id,
            type = type.toString(),
            amount = amount,
            categoryId = categoryId,
            categoryName = categoryName,
            date = date,
            note = note,
            createdAt = createdAt,
        )
}
