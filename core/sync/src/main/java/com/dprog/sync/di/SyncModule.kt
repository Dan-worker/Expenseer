package com.dprog.sync.di

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Koin module for the `core:sync` component.
 *
 * The sync layer will orchestrate offline/online synchronisation and
 * background work. At this time there are no classes to bind, so
 * the module is empty. Add your sync services or managers here when
 * they are created.
 */
val syncModule: Module = module {
    // Register sync dependencies here when available.
}