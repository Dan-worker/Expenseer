package com.dprog.category.di

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Koin module for the `data:category` component.
 *
 * This module will host bindings for category repository
 * implementations and data sources. At the moment no category data
 * layer exists, so the module is empty. Register concrete
 * implementations here as they are created.
 */
val categoryDataModule: Module =
    module {
        // Add category data implementations here.
    }
