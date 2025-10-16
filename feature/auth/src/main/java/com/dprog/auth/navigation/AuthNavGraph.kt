package com.dprog.auth.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dprog.ui.navigation.FeatureNavGraph
import com.dprog.ui.navigation.LoginRoute

class AuthNavGraph : FeatureNavGraph {
    override fun NavGraphBuilder.registerGraph(navController: NavHostController) {
        composable<LoginRoute> {
            Text("Auth")
        }
    }
}
