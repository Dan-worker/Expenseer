package com.dprog.auth.di

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Koin module for the `core:auth` component.
 *
 * This module is intended for authentication primitives such as
 * credential stores or session managers. As no authentication
 * implementation exists yet, the module remains empty. Populate it
 * with singletons or factories when authentication classes are added.
 */
val authCoreModule: Module = module {
    // Bind core authentication dependencies here when implemented.
}
