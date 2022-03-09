package epos_next.app.android.components.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val secondaryColor = Color(0xFF696969)
val errorColor = Color(0xFFDE4844)

@get:Composable
val Colors.textPrimary: Color
    get() = if (isLight) Color(0xFF313131) else Color(0xFFE9EBED)

@get:Composable
val Colors.contrast: Color
    get() = Color(0xFF9569FD)

@get:Composable
val Colors.lightContrast: Color
    get() = Color(0xFFDFD2FE)

@get:Composable
val Colors.disabled: Color
    get() = if (isLight) Color(0xFFF9F9FB) else Color(0xFF1D1A25)

@get:Composable
val Colors.lightError: Color
    get() = Color(0xFFF9DBDB)

@get:Composable
val Colors.lightPrimary: Color
    get() = if (isLight) Color(0xFFCBCBCB) else Color(0xFF888B8E)

@get:Composable
val Colors.skeleton: Color
    get() = if (isLight) Color(0xFFF6F5F9) else Color(0xFF1C1923)