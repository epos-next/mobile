package epos_next.app.android.feats.profile.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import epos_next.app.android.components.theme.textPrimary

@Composable
fun UserName(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier,
        style = TextStyle(
            color = MaterialTheme.colors.textPrimary,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )
    )
}