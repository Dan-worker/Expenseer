package com.dprog.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    NavigationBar {
        // 🏠 Home
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.hasRoute<HomeRoute>() } == true,
            onClick = {
                navController.navigate(HomeRoute) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                }
            },
            label = { Text("Home") },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") }
        )

        // 💰 Transactions
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.hasRoute<TransactionListRoute>() } == true,
            onClick = {
                navController.navigate(TransactionListRoute) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                }
            },
            label = { Text("Transactions") },
            icon = { Icon(Icons.AutoMirrored.Filled.ListAlt, contentDescription = "Transactions") }
        )

        // ➕ Add (center FAB-style button)
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO: navigate to AddTransactionRoute or open dialog */ },
            icon = {
                FloatingActionButton(
                    onClick = { /* TODO: navigate to AddTransactionRoute */ },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                }
            },
            label = { Text("") }, // no label for central FAB
        )

        // 📊 Analytics
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.hasRoute<AnalyticsRoute>() } == true,
            onClick = {
                navController.navigate(AnalyticsRoute) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                }
            },
            label = { Text("Analytics") },
            icon = { Icon(Icons.Filled.Analytics, contentDescription = "Analytics") }
        )

        // ⚙️ Settings
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.hasRoute<SettingsRoute>() } == true,
            onClick = {
                navController.navigate(SettingsRoute) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                }
            },
            label = { Text("Settings") },
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") }
        )
    }
}
