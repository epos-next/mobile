package epos_next.app.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import epos_next.app.android.helpers.UiHelper

@Composable
fun LessonCircle(modifier: Modifier = Modifier, subject: String) {
    val colors = UiHelper.getLessonColor(subject)

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(colors.color)


        ) {
            Text(
                text = subject[0].toString(),
                modifier = Modifier.align(Alignment.Center),
                style = TextStyle(
                    color = colors.colorAccent,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600
                ),
            )
        }
    }
}