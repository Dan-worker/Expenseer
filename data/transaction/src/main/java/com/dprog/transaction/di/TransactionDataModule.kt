package com.dprog.transaction.di

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Koin module for the `data:transaction` component.
 *
 * Bind transaction repository implementations and data sources in
 * this module once they are available. Keeping the module in place
 * now allows the project to compile and signals where transaction
 * specific data classes should be registered.
 */
val transactionDataModule: Module =
    module {
        // Add transaction data implementations here when implemented.
    }
