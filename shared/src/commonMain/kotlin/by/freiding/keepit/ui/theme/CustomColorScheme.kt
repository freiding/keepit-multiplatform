package by.freiding.keepit.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
class CustomColorScheme(
    val colorPrimary: Color,
    val colorSecondary: Color,
    val primaryText: Color,
    val secondaryText: Color,
)

private fun lightCustomColorScheme(): CustomColorScheme = CustomColorScheme(
    colorPrimary = Color.Blue,
    colorSecondary = Color.Cyan,
    primaryText = Color.DarkGray,
    secondaryText = Color.LightGray
)

private fun darkCustomColorScheme(): CustomColorScheme = CustomColorScheme(
    colorPrimary = Color.Blue,
    colorSecondary = Color.Cyan,
    primaryText = Color.DarkGray,
    secondaryText = Color.LightGray
)

@Composable
fun colorScheme(isDark: Boolean = isSystemInDarkTheme()): CustomColorScheme = if (isDark) {
    darkCustomColorScheme()
} else {
    lightCustomColorScheme()
}