package com.dprog.transaction.usecase

import com.dprog.transaction.model.Transaction
import com.dprog.transaction.repository.TransactionRepository

class DeleteTransactionUseCase(
    private val repository: TransactionRepository,
) {
    suspend operator fun invoke(transaction: Transaction) {
        repository.deleteTransaction(transaction)
    }
}
