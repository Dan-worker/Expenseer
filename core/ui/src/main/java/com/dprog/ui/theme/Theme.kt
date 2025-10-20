package com.dprog.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme =
    lightColorScheme(
        background = LightBackground,
        primary = LightPrimary,
        secondary = LightSecondary,
        surface = LightSurface,
        onPrimary = LightText,
        onSecondary = LightOnSurfaceSecondary,
        onSurface = LightOnSurfacePrimary,
        error = LightError,
    )

private val DarkColorScheme =
    darkColorScheme(
        background = DarkBackground,
        primary = DarkPrimary,
        secondary = DarkSecondary,
        surface = DarkSurface,
        onPrimary = DarkText,
        onSecondary = DarkOnSurfaceSecondary,
        onSurface = DarkOnSurfacePrimary,
        error = DarkError,
    )

@Composable
fun ExpenseerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme: ColorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content,
    )
}
