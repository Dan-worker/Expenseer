package com.dprog.mylibrary.di

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Koin module for the `feature:transaction` component.
 *
 * Transaction screens and their state holders will be bound in this
 * module. For now, there are no transaction feature implementations,
 * so the module is empty. When transaction UI is added, register
 * its dependencies here.
 */
val transactionFeatureModule: Module =
    module {
        // Register transaction feature dependencies here when available.
    }
