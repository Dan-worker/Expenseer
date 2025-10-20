package com.dprog.category.usecase

import com.dprog.category.model.Category
import com.dprog.category.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetAllCategoriesUseCase(
    private val repository: CategoryRepository,
) {
    operator fun invoke(): Flow<List<Category>> = repository.getAllCategories()
}
