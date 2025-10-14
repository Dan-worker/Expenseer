package com.dprog.common.di

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Koin module for the `core:common` component.
 *
 * This module exposes common utilities that are shared across the entire
 * application. At present there are no concrete dependencies to bind.
 * When common helpers are introduced (e.g. math utilities, mappers or
 * logging helpers) they should be registered here as singletons or
 * factories. Leaving the module empty preserves the wiring structure.
 */
val commonModule: Module =
    module {
        // Define common dependencies here when they become available.
    }
