package com.dprog.transaction.usecase

import com.dprog.transaction.model.Transaction
import com.dprog.transaction.repository.TransactionRepository
import java.util.UUID

class AddTransactionUseCase(
    private val repository: TransactionRepository,
) {
    suspend operator fun invoke(transaction: Transaction) {
        val newTransaction =
            transaction.copy(
                id = transaction.id.ifEmpty { UUID.randomUUID().toString() },
                createdAt = if (transaction.createdAt == 0L) System.currentTimeMillis() else transaction.createdAt,
            )
        repository.addTransaction(newTransaction)
    }
}
