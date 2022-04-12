package epos_next.app.android.feats.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import epos_next.app.android.components.*
import epos_next.app.android.helpers.UiHelper
import epos_next.app.domain.entities.Lesson
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

data class LessonWithRoomTimeAndTimeLeftProps(
    val lesson: Lesson,
    val timeLeft: Duration
)

private val defaultProps = LessonWithRoomTimeAndTimeLeftProps(
    lesson = Lesson(
        date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
        duration = 45.toDuration(DurationUnit.DAYS),
        groupId = 1,
        id = 1,
        room = "202",
        subject = "Английский язык",
        lessonNumber = 3,
        marks = listOf(4F, 5F),
        truancy = false,
    ),
    timeLeft = 21.toDuration(DurationUnit.MINUTES),
)

@Preview
@Composable
fun LessonWithRoomTimeAndTimeLeft(
    modifier: Modifier = Modifier.fillMaxWidth(),
    props: LessonWithRoomTimeAndTimeLeftProps = defaultProps
) {

    Box(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            LessonCircle(subject = props.lesson.subject)

            Box(
                modifier = Modifier.padding(start = 15.dp)
            ) {
                Column {
                    LessonSubject(text = props.lesson.subject)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        LessonSubtitle(text = props.lesson.room)
                        LessonSubtitleDot()
                        LessonSubtitle(
                            text = UiHelper.formatLessonTime(
                                props.lesson.date,
                                props.lesson.duration
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1.0f))

            LessonTimeLeft(timeLeft = props.timeLeft)
        }
    }
}


