package com.dprog.ui.di

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Koin module for the `core:ui` component.
 *
 * UI‑related helpers (such as image loaders or theme controllers) can be
 * provided through this module. Until such classes exist, the module
 * remains empty. Creating the module now ensures that when UI
 * dependencies are introduced they can be injected without modifying
 * the application entry point.
 */
val uiModule: Module = module {
    // Define UI dependencies here when they become available.
}