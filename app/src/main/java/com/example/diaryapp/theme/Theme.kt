package com.example.diaryapp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color



private val DarkColorPalette = darkColorScheme(
    primary = Background,
    inversePrimary = Background2,
    secondary = Background2
)

private val LightColorPalette = lightColorScheme(
    primary = Background,
    inversePrimary = Background2,
    secondary = Background,
    background = Color.Transparent,
    surface = Color.Transparent,
)


@Composable
fun DiaryAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,

    ) {
        content()
    }
}