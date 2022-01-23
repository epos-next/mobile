package epos_next.app.state.nextLesson

import epos_next.app.domain.entities.Lesson
import kotlin.time.Duration

sealed class NextLessonState {
    object Loading: NextLessonState()

    object NotStudyingTime: NextLessonState()

    abstract class Idle: NextLessonState() {
        abstract val nextLesson: Lesson
        abstract val timeLeft: Duration // 22:47
    }

    data class LessonTime(
        override val nextLesson: Lesson,
        override val timeLeft: Duration,
    ): Idle()

    data class Break(
        override val nextLesson: Lesson,
        override val timeLeft: Duration,
    ): Idle()
}
