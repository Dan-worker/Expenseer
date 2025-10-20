package com.dprog.transaction.di

import com.dprog.transaction.repository.TransactionRepository
import com.dprog.transaction.repository.TransactionRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Koin module for the `data:transaction` component.
 */
val transactionDataModule: Module =
    module {
        single<TransactionRepository> { TransactionRepositoryImpl(get()) }
    }
