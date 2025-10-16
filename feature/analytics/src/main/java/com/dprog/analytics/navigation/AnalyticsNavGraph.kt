package com.dprog.analytics.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dprog.ui.navigation.AnalyticsRoute
import com.dprog.ui.navigation.FeatureNavGraph

class AnalyticsNavGraph : FeatureNavGraph {
    override fun NavGraphBuilder.registerGraph(navController: NavHostController) {
        composable<AnalyticsRoute> {
            Text("Analytics")
        }
    }
}
