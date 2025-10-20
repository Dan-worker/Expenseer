package com.dprog.category.di

import com.dprog.category.usecase.GetAllCategoriesUseCase
import com.dprog.category.usecase.GetCategoriesByTypeUseCase
import com.dprog.category.usecase.InitializeDefaultCategoriesUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Koin module for the `domain:category` component.
 *
 * Domain modules expose business logic in the form of use cases. No
 * category use cases exist yet, so this module is empty. As the
 * application grows, register your category use cases here with
 * `factory` or `single` definitions as appropriate.
 */
val categoryDomainModule: Module =
    module {
        single { GetAllCategoriesUseCase(get()) }
        single { GetCategoriesByTypeUseCase(get()) }
        single { InitializeDefaultCategoriesUseCase(get()) }
    }
