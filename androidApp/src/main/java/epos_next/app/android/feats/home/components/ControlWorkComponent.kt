package epos_next.app.android.feats.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import epos_next.app.android.components.LessonCircle
import epos_next.app.android.components.LessonSubject
import epos_next.app.android.components.LessonSubtitle
import epos_next.app.domain.entities.ControlWork
import epos_next.app.utils.FormatHelper
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.DurationUnit
import kotlin.time.toDuration

private val defaultProps = ControlWork(
    id = 1,
    date = Clock.System.now().plus(3.toDuration(DurationUnit.DAYS))
        .toLocalDateTime(TimeZone.currentSystemDefault()),
    lesson = "Алгебра",
    name = "Степени с целым показателем"
)

@Composable
fun ControlWorkComponent(
    modifier: Modifier = Modifier,
    controlWork: ControlWork = defaultProps
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.fillMaxWidth()) {
        LessonCircle(subject = controlWork.lesson)

        Column(modifier = Modifier.padding(start = 15.dp)) {
            LessonSubject(text = controlWork.lesson)
            LessonSubtitle(text = controlWork.name)
        }

        Spacer(modifier = Modifier.weight(1.0f))

        Text(
            text = FormatHelper.futureDate(controlWork.date),
            style = TextStyle(
                fontSize = 13.sp,
                color = MaterialTheme.colors.secondary
            )
        )
    }
}