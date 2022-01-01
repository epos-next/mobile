package epos_next.app.android.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import epos_next.app.android.components.theme.contrast
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun LessonTimeLeft(modifier: Modifier = Modifier, timeLeft: Duration) {
    val date = LocalDateTime.of(1, 1, 1, 0, 0).plus(timeLeft)
    val formatter = DateTimeFormatter.ofPattern("mm:ss")
    val formattedTimeLeft = date.format(formatter)

    Text(
        modifier = modifier,
        text = formattedTimeLeft,
        style = TextStyle(
            fontSize = 20.sp,
            color = MaterialTheme.colors.contrast,
        )
    )
}