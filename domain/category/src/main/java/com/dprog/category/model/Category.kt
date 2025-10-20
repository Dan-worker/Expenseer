package com.dprog.category.model

data class Category(
    val id: String,
    val name: String,
    val icon: String,
    val color: String,
    val type: CategoryType,
)
