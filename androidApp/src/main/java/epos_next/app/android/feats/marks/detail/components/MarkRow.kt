package epos_next.app.android.feats.marks.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import epos_next.app.android.components.theme.contrast
import epos_next.app.android.components.theme.lightContrast
import epos_next.app.android.components.theme.textPrimary

@Composable
fun PrimaryMarkRow(
    modifier: Modifier = Modifier,
    name: String,
    value: String,
    accent: Boolean = false,
    valueActive: Boolean = true
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        Arrangement.SpaceBetween
    ) {
        Text(
            text = name,
            style = TextStyle(
                fontSize = 16.sp,
                color = if (accent) MaterialTheme.colors.textPrimary else MaterialTheme.colors.secondary,
            ),
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            text = value,
            style = TextStyle(
                fontSize = 16.sp,
                color = if (valueActive) MaterialTheme.colors.contrast else MaterialTheme.colors.lightContrast,
            )
        )
    }
}
