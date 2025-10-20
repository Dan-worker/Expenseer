package com.dprog.transaction.model

enum class TransactionType {
    INCOME,
    EXPENSE,
    ;

    companion object {
        fun fromString(value: String): TransactionType =
            when (value.lowercase()) {
                "income" -> INCOME
                "expense" -> EXPENSE
                else -> EXPENSE
            }
    }

    fun toDisplayString(): String =
        when (this) {
            INCOME -> "Income"
            EXPENSE -> "Expense"
        }

    override fun toString(): String = name.lowercase()
}
