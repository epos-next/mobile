package epos_next.app.android.components.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    background = Color(0xFF11111B),
    surface = Color(0xFF1D1A25),
    secondary = Color(0xFF8A8A93),
    error = errorColor,
)

private val LightColorPalette = lightColors(
    secondary = secondaryColor,
    error = errorColor,
)

const val BackgroundPreviewColor = 0xFFFFFFFF

@Composable
fun ApplicationTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        content = content
    )
}