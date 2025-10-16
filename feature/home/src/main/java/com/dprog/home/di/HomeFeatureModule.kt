package com.dprog.home.di

import com.dprog.home.navigation.HomeNavGraph
import com.dprog.ui.navigation.FeatureNavGraph
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Koin module for the `feature:home` component.
 *
 * Home screen ViewModels and supporting classes should be bound in this module.
 */
val homeFeatureModule: Module =
    module {
        single<FeatureNavGraph>(named("home")) { HomeNavGraph() }
    }
