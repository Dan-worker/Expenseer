package com.dprog.category.repository

import com.dprog.category.model.Category
import com.dprog.category.model.CategoryType
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getAllCategories(): Flow<List<Category>>

    fun getCategoriesByType(type: CategoryType): Flow<List<Category>>

    suspend fun getCategoryById(id: String): Category?

    suspend fun addCategory(category: Category)

    suspend fun addDefaultCategories()
}
