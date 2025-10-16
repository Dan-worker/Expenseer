package com.dprog.expenseer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dprog.ui.navigation.FeatureNavGraph
import com.dprog.ui.navigation.LoginRoute
import org.koin.compose.koinInject
import org.koin.core.qualifier.named

/**
 * Main navigation host for the application.
 *
 * Collects all feature navigation graphs from Koin and registers them
 * with the NavHost. The app starts at the LoginRoute by default.
 */
@Composable
fun AppNavigationHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    // Inject all feature navigation graphs
    val authGraph: FeatureNavGraph = koinInject(named("auth"))
    val homeGraph: FeatureNavGraph = koinInject(named("home"))
    val transactionGraph: FeatureNavGraph = koinInject(named("transaction"))
    val analyticsGraph: FeatureNavGraph = koinInject(named("analytics"))

    NavHost(
        navController = navController,
        startDestination = LoginRoute,
        modifier = modifier
    ) {
        // Register all feature graphs
        with(authGraph) { registerGraph(navController) }
        with(homeGraph) { registerGraph(navController) }
        with(transactionGraph) { registerGraph(navController) }
        with(analyticsGraph) { registerGraph(navController) }
    }
}
