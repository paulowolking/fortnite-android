package com.wolking.fortnite.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun FortnaticosTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkColors else LightColors,
        typography = FortnaticosTypography,
        content = content,
    )
}

private val LightColors = lightColors(
    primary = PurplePrimary,
    primaryVariant = PurplePrimaryDark,
    onPrimary = Color.White,
    secondary = OrangeAccent,
    secondaryVariant = OrangeAccent,
    onSecondary = Color.White,
    error = Red400
)

private val DarkColors = darkColors(
    primary = PurplePrimary,
    primaryVariant = PurplePrimaryDark,
    onPrimary = Color.White,
    secondary = OrangeAccent,
    secondaryVariant = OrangeAccent,
    onSecondary = Color.White,
    error = Red400
)