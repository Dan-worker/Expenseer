package com.dprog.category.usecase

import com.dprog.category.repository.CategoryRepository

class InitializeDefaultCategoriesUseCase(
    private val repository: CategoryRepository,
) {
    suspend operator fun invoke() {
        repository.addDefaultCategories()
    }
}
