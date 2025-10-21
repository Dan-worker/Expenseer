package com.dprog.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dprog.auth.ui.AuthScreen
import com.dprog.ui.navigation.FeatureNavGraph
import com.dprog.ui.navigation.HomeRoute
import com.dprog.ui.navigation.LoginRoute

class AuthNavGraph : FeatureNavGraph {
    override fun NavGraphBuilder.registerGraph(navController: NavHostController) {
        composable<LoginRoute> {
            AuthScreen(
                onSignedIn = {
                    navController.navigate(HomeRoute) {
                        popUpTo(LoginRoute) { inclusive = true }
                        launchSingleTop = true
                    }
                },
            )
        }
    }
}
