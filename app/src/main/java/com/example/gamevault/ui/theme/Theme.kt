package com.example.gamevault.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val GamerColorScheme = darkColorScheme(
    primary = GamerGreen,
    onPrimary = Color.Black,
    primaryContainer = GamerGreenDark,
    onPrimaryContainer = Color.Black,
    secondary = GamerGreen,
    onSecondary = Color.Black,
    background = Color.Black,
    onBackground = Color.White,
    surface = Color.Black,
    onSurface = Color.White,
    surfaceVariant = GamerCard,
    onSurfaceVariant = Color.White
)

@Composable
fun GameVaultTheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = GamerColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
