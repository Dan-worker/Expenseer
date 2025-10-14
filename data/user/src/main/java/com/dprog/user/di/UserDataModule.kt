package com.dprog.user.di

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Koin module for the `data:user` component.
 *
 * User data repository implementations should be registered in this
 * module. There are no user data classes yet, so the module is
 * currently empty. Populate it with concrete implementations as
 * they are introduced.
 */
val userDataModule: Module =
    module {
        // Add user data implementations here when available.
    }
