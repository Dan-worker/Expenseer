package com.dprog.home.di

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Koin module for the `feature:home` component.
 *
 * Home screen ViewModels and supporting classes should be bound in this
 * module. As the home feature does not yet exist, the module is
 * deliberately empty. When you add home screen logic, register it
 * here as a factory or singleton.
 */
val homeFeatureModule: Module =
    module {
        // Register home feature dependencies here when implemented.
    }
