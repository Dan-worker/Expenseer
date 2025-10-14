package com.dprog.transaction.di

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Koin module for the `domain:transaction` component.
 *
 * The transaction domain layer will provide business logic related to
 * transactions. Since there are no use cases yet, this module is left
 * empty. Add factories or singletons for your use cases here when they
 * are created.
 */
val transactionDomainModule: Module = module {
    // Register transaction domain use cases here.
}