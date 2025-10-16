package com.dprog.analytics.di

import com.dprog.analytics.navigation.AnalyticsNavGraph
import com.dprog.ui.navigation.FeatureNavGraph
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Koin module for the `feature:analytics` component.
 *
 * Bindings for analytics feature ViewModels and other UI layer
 * abstractions belong here.
 */
val analyticsFeatureModule: Module =
    module {
        single<FeatureNavGraph>(named("analytics")) { AnalyticsNavGraph() }
    }
