package epos_next.app.android.feats.login.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import epos_next.app.android.components.theme.contrast

@Composable
fun EposName() {
    Text(
        text = "Эпос Next",
        fontSize = 24.sp,
        fontWeight = FontWeight.W600,
        color = MaterialTheme.colors.contrast
    )
}