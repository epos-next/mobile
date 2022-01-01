package epos_next.app.android.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp
import epos_next.app.android.components.theme.disabled

@Composable
fun LessonDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = Modifier.fillMaxWidth().composed { modifier },
        thickness = 1.5.dp,
        color = MaterialTheme.colors.disabled
    )
}