package epos_next.app.android.feats.home.components

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import epos_next.app.android.components.LessonCircle
import epos_next.app.android.components.LessonSubject
import epos_next.app.android.components.LessonSubtitle
import epos_next.app.android.components.LessonSubtitleDot
import epos_next.app.android.helpers.UiHelper
import epos_next.app.domain.entities.Lesson
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.DurationUnit
import kotlin.time.toDuration

private val defaultProps = Lesson(
    date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
    duration = 45.toDuration(DurationUnit.MINUTES),
    groupId = 1,
    id = 1,
    room = "202",
    subject = "Английский язык",
    lessonNumber = 3,
)

@Preview
@Composable
fun LessonWithRoomAndTime(
    modifier: Modifier = Modifier,
    lesson: Lesson = defaultProps
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth().height(40.dp)
    ) {
        LessonCircle(subject = lesson.subject)

        Column(
            modifier = Modifier
                .padding(start = 15.dp)
        ) {
            LessonSubject(text = lesson.subject)
            Row(verticalAlignment = Alignment.CenterVertically) {
                LessonSubtitle(text = lesson.room)
                LessonSubtitleDot()
                LessonSubtitle(
                    text = UiHelper.formatLessonTime(
                        lesson.date,
                        lesson.duration
                    )
                )
            }
        }

    }
}