package com.dprog.category.model

enum class CategoryType {
    INCOME,
    EXPENSE,
    ;

    companion object {
        fun fromString(value: String): CategoryType =
            when (value.lowercase()) {
                "income" -> INCOME
                "expense" -> EXPENSE
                else -> EXPENSE
            }
    }

    override fun toString(): String = name.lowercase()
}
