package com.edumate.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun EduMateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = BlueDark,
            secondary = BlueGreyDark,
            tertiary = SeaBlueDark
        )
    } else {
        lightColorScheme(
            primary = BlueLight,
            secondary = BlueGreyLight,
            tertiary = SeaBlueLight
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
