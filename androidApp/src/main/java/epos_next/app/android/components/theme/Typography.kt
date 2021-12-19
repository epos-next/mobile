package epos_next.app.android.components.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import epos_next.app.android.R

val AvenirNext = FontFamily(
    Font(R.font.avenirnext_regular),
    Font(R.font.avenirnext_medium, weight = FontWeight.W500),
    Font(R.font.avenirnext_demi, weight = FontWeight.W600),
)

val Typography = Typography(
    h2 = TextStyle(
        fontSize = 28.sp,
        fontFamily = AvenirNext,
        fontWeight = FontWeight.W600,
    ),
    h3 = TextStyle(
        fontSize = 18.sp,
        fontFamily = AvenirNext,
        fontWeight = FontWeight.W500,
    ),
    h4 = TextStyle(
        fontSize = 15.sp,
        fontFamily = AvenirNext,
        fontWeight = FontWeight.W500,
    ),
    body1 = TextStyle(
        fontSize = 17.sp,
        fontFamily = AvenirNext,
    ),
    body2 = TextStyle(
        fontSize = 15.sp,
        fontFamily = AvenirNext,
    )
)