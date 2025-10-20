package com.dprog.category.di

import com.dprog.category.repository.CategoryRepository
import com.dprog.category.repository.CategoryRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Koin module for the `data:category` component.
 */
val categoryDataModule: Module = module {
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
}
