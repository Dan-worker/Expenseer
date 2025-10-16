package com.dprog.transaction.di

import com.dprog.transaction.navigation.TransactionNavGraph
import com.dprog.ui.navigation.FeatureNavGraph
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Koin module for the `feature:transaction` component.
 */
val transactionFeatureModule: Module =
    module {
        single<FeatureNavGraph>(named("transaction")) { TransactionNavGraph() }
    }
