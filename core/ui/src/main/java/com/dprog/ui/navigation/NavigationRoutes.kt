package com.dprog.ui.navigation

import kotlinx.serialization.Serializable

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
data class TransactionDetailRoute(
    val transactionId: String,
)

@Serializable
data class AddTransactionRoute(
    val categoryId: String? = null,
)

@Serializable
data class EditTransactionRoute(
    val transactionId: String,
)

// Analytics feature routes
@Serializable
object AnalyticsRoute

@Serializable
data class CategoryAnalyticsRoute(
    val categoryId: String,
)

// Settings
@Serializable
object SettingsRoute


