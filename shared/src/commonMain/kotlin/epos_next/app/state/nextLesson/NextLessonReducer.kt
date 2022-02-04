package epos_next.app.state.nextLesson

import epos_next.app.data.lessons.LessonsDataSource
import epos_next.app.domain.entities.Lesson
import epos_next.app.lib.BaseReducer
import io.github.aakira.napier.Napier
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.*
import org.koin.core.component.inject
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

class NextLessonReducer : BaseReducer<NextLessonState>(NextLessonState.Loading) {

    private val lessonsDataSource: LessonsDataSource by inject()

    private var nextLessonCalculationJob: Job? = null

    fun startCalculationProcess() {
        nextLessonCalculationJob?.cancel()
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val lessons = lessonsDataSource.getByDate(now)?.sortedBy { it.date } ?: return

        nextLessonCalculationJob = scope.launch {
            calculateNextLesson(lessons)
            Napier.d("finished!")
        }
    }

    fun reset() {
        nextLessonCalculationJob?.cancel()
        stateFlow.update { NextLessonState.Loading }
    }

    @OptIn(ExperimentalTime::class)
    private suspend fun calculateNextLesson(lessons: List<Lesson>) {

        // checking lessons for today
        if (lessons.isEmpty()) {
            return stateFlow.emit(NextLessonState.NotStudyingTime)
        }

        var now = Clock.System.now()
        val tz = TimeZone.currentSystemDefault()

        // get nearest lesson
        val nearestLesson = lessons.first()

        // checking is next lesson in nearest 1 hours.
        // That's need to not show "Next lesson is biology" in 3am
        if ((nearestLesson.date.toInstant(tz) - now).toDouble(DurationUnit.HOURS) > 1) {
            Napier.d(nearestLesson.date.toInstant(tz).toString(), tag = "calculateNextLesson")
            return stateFlow.update { NextLessonState.NotStudyingTime }
        }

        // Calculating thinking now is a lessons
        for (i in lessons.indices) {
            val lesson = lessons[i]

            // calculated start and end date using duration property
            val lessonsStartTime = lesson.date.toInstant(tz)
            val lessonEndTime = lessonsStartTime.plus(lesson.duration)

            // if now not this lesson -> skip
            if (now < lessonsStartTime || now > lessonEndTime) continue

            // found lesson and calculation all necessary data
            var diff = lessonEndTime - Clock.System.now()
            while (!diff.isNegative()) {
                stateFlow.update {
                    NextLessonState.LessonTime(
                        nextLesson = lesson,
                        timeLeft = diff,
                    )
                }
                diff = lessonEndTime - Clock.System.now()
                delay(1000)
            }

            return calculateNextLesson(lessons)
        }

        Napier.d("not lesson", tag = "calculateNextLesson")

        now = Clock.System.now()

        // Calculating think now is a lesson break
        var breakStart = Clock.System.now().minus(1.toDuration(DurationUnit.DAYS))
        for (i in lessons.indices) {
            val lesson = lessons[i]

            // calculated start and end date using duration property
            val lessonsStartTime = lesson.date.toInstant(tz)
            val lessonEndTime = lessonsStartTime.plus(lesson.duration)

            // if now not this break before this lesson -> skip
            if (now < breakStart || now > lessonsStartTime) {
                breakStart = lessonEndTime
                continue
            }

            var diff = lessonsStartTime - Clock.System.now()
            while (!diff.isNegative()) {
                stateFlow.update {
                    NextLessonState.Break(
                        nextLesson = lesson,
                        timeLeft = diff,
                    )
                }
                diff = lessonsStartTime - Clock.System.now()
                delay(1000)
            }

            return calculateNextLesson(lessons)
        }

        Napier.d("not break", tag = "calculateNextLesson")

        stateFlow.update { NextLessonState.NotStudyingTime }
    }
}