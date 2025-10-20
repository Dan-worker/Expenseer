package com.dprog.ui.navigation

import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val destination = backStackEntry?.destination

    NavigationBar {
        // 🏠 Home
        DefaultNavItem(
            label = "Home",
            icon = Icons.Filled.Home,
            selected = destination?.hierarchy?.any { it.hasRoute<HomeRoute>() } == true,
        ) {
            navController.navigate(HomeRoute) {
                launchSingleTop = true
                restoreState = true
                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            }
        }

        // 💰 Transactions
        DefaultNavItem(
            label = "Transactions",
            icon = Icons.AutoMirrored.Filled.ListAlt,
            selected = destination?.hierarchy?.any { it.hasRoute<TransactionListRoute>() } == true,
        ) {
            navController.navigate(TransactionListRoute) {
                launchSingleTop = true
                restoreState = true
                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            }
        }

        // ➕ Add (center FAB)
        AddCenterItem { navController.navigate(AddTransactionRoute()) }

        // 📊 Analytics
        DefaultNavItem(
            label = "Analytics",
            icon = Icons.Filled.Analytics,
            selected = destination?.hierarchy?.any { it.hasRoute<AnalyticsRoute>() } == true,
        ) {
            navController.navigate(AnalyticsRoute) {
                launchSingleTop = true
                restoreState = true
                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            }
        }

        // ⚙️ Settings
        DefaultNavItem(
            label = "Settings",
            icon = Icons.Filled.Settings,
            selected = destination?.hierarchy?.any { it.hasRoute<SettingsRoute>() } == true,
        ) {
            navController.navigate(SettingsRoute) {
                launchSingleTop = true
                restoreState = true
                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            }
        }
    }
}

/** Reusable item for standard sections — MUST be a RowScope extension */
@Composable
fun RowScope.DefaultNavItem(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        label = { Text(label) },
        icon = { Icon(icon, contentDescription = label) },
    )
}

/** Center FAB-style “Add” — also a RowScope extension */
@Composable
fun RowScope.AddCenterItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        selected = false, // FAB actions typically aren't "selected"
        onClick = { /* handled by FAB below */ },
        icon = {
            FloatingActionButton(
                onClick = onClick,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = modifier,
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        },
        label = { Text("") },
    )
}
