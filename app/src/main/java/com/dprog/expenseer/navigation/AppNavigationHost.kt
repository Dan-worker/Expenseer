package com.dprog.expenseer.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dprog.ui.navigation.BottomNavigationBar
import com.dprog.ui.navigation.FeatureNavGraph
import com.dprog.ui.navigation.LoginRoute
import org.koin.compose.koinInject
import org.koin.core.qualifier.named

@Composable
fun AppNavigationHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    // Inject feature graphs
    val authGraph: FeatureNavGraph = koinInject(named("auth"))
    val homeGraph: FeatureNavGraph = koinInject(named("home"))
    val transactionGraph: FeatureNavGraph = koinInject(named("transaction"))
    val analyticsGraph: FeatureNavGraph = koinInject(named("analytics"))
    val settingsGraph: FeatureNavGraph = koinInject(named("settings"))

    // Scaffold with bottom bar
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        modifier = modifier,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = LoginRoute,
            modifier = Modifier.padding(innerPadding),
        ) {
            with(authGraph) { registerGraph(navController) }
            with(homeGraph) { registerGraph(navController) }
            with(transactionGraph) { registerGraph(navController) }
            with(analyticsGraph) { registerGraph(navController) }
            with(settingsGraph) { registerGraph(navController) }
        }
    }
}
