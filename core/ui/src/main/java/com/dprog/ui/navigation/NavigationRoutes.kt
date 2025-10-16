package com.dprog.ui.navigation

import kotlinx.serialization.Serializable

/**
 * Type-safe navigation routes for the application.
 *
 * Each route is a serializable data class or object that represents
 * a destination in the app. Navigation 3 uses these types to provide
 * compile-time safety when navigating between screens.
 */

// Auth feature routes
@Serializable
object LoginRoute

@Serializable
object RegisterRoute

// Home feature routes
@Serializable
object HomeRoute

// Transaction feature routes
@Serializable
object TransactionListRoute

@Serializable
data class TransactionDetailRoute(val transactionId: String)

@Serializable
data class AddTransactionRoute(val categoryId: String? = null)

@Serializable
data class EditTransactionRoute(val transactionId: String)

// Analytics feature routes
@Serializable
object AnalyticsRoute

@Serializable
data class CategoryAnalyticsRoute(val categoryId: String)
