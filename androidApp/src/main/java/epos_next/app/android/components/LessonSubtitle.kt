package epos_next.app.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LessonSubtitle(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier,
        text = text,
        overflow = TextOverflow.Visible,
        style = TextStyle(
            color = MaterialTheme.colors.secondary,
            fontSize = 14.sp,
        )
    )
}

@Composable
fun LessonSubtitleDot() {
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .size(3.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colors.secondary)
    )
}