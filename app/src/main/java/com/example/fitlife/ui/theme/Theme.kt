package com.example.fitlife.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = androidx.compose.ui.graphics.Color(0xFF1B5E20),
    primaryVariant = androidx.compose.ui.graphics.Color(0xFF2E7D32),
    secondary = androidx.compose.ui.graphics.Color(0xFF81C784)
)

@Composable
fun FitLifeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = DarkColorPalette,
        content = content
    )
}
