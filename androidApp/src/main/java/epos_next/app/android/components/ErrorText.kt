package epos_next.app.android.components

import androidx.compose.animation.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text,
        fontSize = 16.sp,
        color = MaterialTheme.colors.error,
        textAlign = textAlign,
        modifier = modifier,
        maxLines = 2,
    )
}