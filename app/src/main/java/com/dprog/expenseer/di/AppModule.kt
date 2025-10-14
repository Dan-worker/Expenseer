package com.dprog.expenseer.di

import com.dprog.analytics.di.analyticsFeatureModule
import com.dprog.auth.di.authCoreModule
import com.dprog.auth.di.authFeatureModule
import com.dprog.category.di.categoryDataModule
import com.dprog.category.di.categoryDomainModule
import com.dprog.common.di.commonModule
import com.dprog.database.di.databaseModule
import com.dprog.home.di.homeFeatureModule
import com.dprog.mylibrary.di.transactionFeatureModule
import com.dprog.network.di.networkModule
import com.dprog.sync.di.syncModule
import com.dprog.transaction.di.transactionDataModule
import com.dprog.transaction.di.transactionDomainModule
import com.dprog.ui.di.uiModule
import com.dprog.user.di.userDataModule
import com.dprog.user.di.userDomainModule
import org.koin.core.module.Module

/**
 * Top‑level Koin module aggregator.
 *
 * The application module coordinates dependency injection across all
 * feature, domain, data and core modules. Each sub‑module defines its
 * own Koin module (e.g. `categoryDomainModule` or `networkModule`), and
 * this list collects them so that the Koin container can load every
 * definition at application startup. By keeping an explicit list here
 * rather than relying on reflection, we avoid hidden wiring and make it
 * obvious which modules participate in dependency injection.
 */
val appModules: List<Module> = listOf(
    // Core modules
    commonModule,
    databaseModule,
    networkModule,
    authCoreModule,
    syncModule,
    uiModule,

    // Data modules
    categoryDataModule,
    transactionDataModule,
    userDataModule,

    // Domain modules
    categoryDomainModule,
    transactionDomainModule,
    userDomainModule,

    // Feature modules
    analyticsFeatureModule,
    authFeatureModule,
    homeFeatureModule,
    transactionFeatureModule,
)
