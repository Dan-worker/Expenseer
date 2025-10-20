package com.dprog.transaction.usecase

import com.dprog.transaction.model.Transaction
import com.dprog.transaction.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class GetAllTransactionsUseCase(
    private val repository: TransactionRepository,
) {
    operator fun invoke(): Flow<List<Transaction>> = repository.getAllTransactions()
}
