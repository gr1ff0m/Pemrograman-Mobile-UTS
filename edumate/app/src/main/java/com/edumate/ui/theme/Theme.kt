package com.edumate.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = BlueDark,          // Dark Blue
    secondary = SeaBlueDark,    // Dark Sea Blue
    background = BlueGreyDark,  // Dark Blue-Grey
    surface = BlueGreyDark,     // Dark Blue-Grey
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = BlueLight,          // Light Blue
    secondary = SeaBlueLight,    // Light Sea Blue
    background = BlueGreyLight,  // Light Blue-Grey
    surface = BlueGreyLight,     // Light Blue-Grey
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)
