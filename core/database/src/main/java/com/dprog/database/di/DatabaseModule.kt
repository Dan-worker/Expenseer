package com.dprog.database.di

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Koin module for the `core:database` component.
 *
 * Use this module to register Room database instances, DAOs and other
 * persistence‑related dependencies. It is intentionally empty until
 * database classes are implemented. Defining it now allows the
 * application to compile and provides a clear location for future
 * bindings.
 */
val databaseModule: Module =
    module {
        // Register database dependencies here when they exist.
    }
