package com.dprog.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

/**
 * A base interface for defining feature-specific navigation graphs.
 *
 * Each feature module (e.g. Auth, Home, Settings) can implement this interface
 * to register its own navigation destinations within the shared NavHost.
 */
interface FeatureNavGraph {
    fun NavGraphBuilder.registerGraph(navController: NavHostController)
}

