package epos_next.app.android.feats.marks.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import epos_next.app.android.components.LessonCircle
import epos_next.app.android.components.LessonSubject
import epos_next.app.android.components.LessonSubtitle
import epos_next.app.android.components.TotalNumber
import epos_next.app.android.components.theme.contrast
import epos_next.app.android.components.theme.lightContrast
import kotlin.math.round
import kotlin.math.roundToInt


@Preview(showBackground = true)
@Composable
fun LessonWithMarks(
    modifier: Modifier = Modifier,
    lessonName: String = "Физика",
    marks: List<Int> = listOf(5, 4, 5, 3, 2),
    totalMark: Int? = null,
    onClick: () -> Unit = {},
) {
    val totalExpected = if (marks.isNotEmpty()) (marks.sum().toDouble() / marks.size).roundToInt()
    else 0

    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        LessonCircle(subject = lessonName)

        Column(modifier = Modifier.padding(start = 15.dp)) {
            LessonSubject(text = lessonName)
            Row {
                for (mark in marks) {
                    LessonSubtitle(
                        modifier = Modifier.padding(end = 20.dp),
                        text = mark.toString()
                    )
                }
            }
        }


        Spacer(modifier = Modifier.weight(1f))
        if (totalExpected != 0 || totalMark != null) {
            TotalNumber(
                totalMark ?: totalExpected,
                active = totalMark != null
            )
        }
    }

}
