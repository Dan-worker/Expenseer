package com.dprog.home.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dprog.ui.navigation.FeatureNavGraph
import com.dprog.ui.navigation.HomeRoute

class HomeNavGraph : FeatureNavGraph {
    override fun NavGraphBuilder.registerGraph(navController: NavHostController) {
        composable<HomeRoute> {
            Text("Home")
        }
    }
}
