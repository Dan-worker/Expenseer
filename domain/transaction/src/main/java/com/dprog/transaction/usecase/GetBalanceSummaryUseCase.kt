package com.dprog.transaction.usecase

import com.dprog.transaction.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetBalanceSummaryUseCase(
    private val repository: TransactionRepository,
) {
    data class BalanceSummary(
        val totalIncome: Double,
        val totalExpense: Double,
        val balance: Double,
    )

    operator fun invoke(): Flow<BalanceSummary> =
        combine(
            repository.getTotalIncome(),
            repository.getTotalExpense(),
        ) { income, expense ->
            BalanceSummary(
                totalIncome = income,
                totalExpense = expense,
                balance = income - expense,
            )
        }
}
