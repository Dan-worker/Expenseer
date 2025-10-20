package com.dprog.settings.di

import com.dprog.settings.navigation.SettingsNavGraph
import com.dprog.ui.navigation.FeatureNavGraph
import org.koin.core.qualifier.named
import org.koin.dsl.module

val settingsFeatureModule =
    module {
        single<FeatureNavGraph>(named("settings")) { SettingsNavGraph() }
    }
