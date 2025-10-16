package com.dprog.auth.di

import com.dprog.auth.navigation.AuthNavGraph
import com.dprog.ui.navigation.FeatureNavGraph
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Koin module for the `feature:auth` component.
 */
val authFeatureModule: Module =
    module {
        single<FeatureNavGraph>(named("auth")) { AuthNavGraph() }
    }
