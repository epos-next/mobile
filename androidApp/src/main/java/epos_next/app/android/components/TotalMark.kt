package epos_next.app.android.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import epos_next.app.android.components.theme.contrast
import epos_next.app.android.components.theme.lightContrast

@Composable
fun TotalNumber(number: Int, modifier: Modifier = Modifier, active: Boolean = true) {
    Text(
        modifier = modifier,
        text = number.toString(),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
            color = if (active) MaterialTheme.colors.contrast else MaterialTheme.colors.lightContrast
        )
    )
}