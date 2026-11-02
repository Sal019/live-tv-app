package com.salman.livetv.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Premium dark theme, Toffee-style deep purple + live red accent.
val DeepPurple = Color(0xFF1B1035)
val SurfacePurple = Color(0xFF241843)
val CardPurple = Color(0xFF2E2152)
val LiveRed = Color(0xFFE50914)
val AccentViolet = Color(0xFF8B5CF6)
val TextPrimary = Color(0xFFFFFFFF)
val TextSecondary = Color(0xFFB8AED4)

private val DarkScheme = darkColorScheme(
    primary = AccentViolet,
    secondary = LiveRed,
    background = DeepPurple,
    surface = SurfacePurple,
    surfaceVariant = CardPurple,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

private val AppTypography = Typography(
    headlineMedium = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp),
    titleMedium = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 16.sp),
    bodyMedium = TextStyle(fontSize = 14.sp, color = TextSecondary),
    labelMedium = TextStyle(fontWeight = FontWeight.Medium, fontSize = 12.sp)
)

@Composable
fun LiveTvTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkScheme,
        typography = AppTypography,
        content = content
    )
}
