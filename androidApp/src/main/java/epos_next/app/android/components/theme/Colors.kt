package epos_next.app.android.components.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val secondaryColor = Color(0xFF696969)
val errorColor = Color(0xFFDE4844)

@get:Composable
val Colors.textPrimary: Color
    get() = Color(0xFF313131)

@get:Composable
val Colors.contrast: Color
    get() = Color(0xFF9569FD)

@get:Composable
val Colors.lightContrast: Color
    get() = Color(0xFFDFD2FE)

@get:Composable
val Colors.disabled: Color
    get() = Color(0xFFF9F9FB)

@get:Composable
val Colors.lightError: Color
    get() = Color(0xFFF9DBDB)

@get:Composable
val Colors.lightPrimary: Color
    get() = Color(0xFFCBCBCB)

@get:Composable
val Colors.skeleton: Color
    get() = Color(0xFFF6F5F9)