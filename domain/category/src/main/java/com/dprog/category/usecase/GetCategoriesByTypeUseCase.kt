package com.dprog.category.usecase

import com.dprog.category.model.Category
import com.dprog.category.model.CategoryType
import com.dprog.category.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetCategoriesByTypeUseCase(
    private val repository: CategoryRepository,
) {
    operator fun invoke(type: CategoryType): Flow<List<Category>> = repository.getCategoriesByType(type)
}
