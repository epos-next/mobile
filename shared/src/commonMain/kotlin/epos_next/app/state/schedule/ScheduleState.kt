package epos_next.app.state.schedule

import epos_next.app.domain.entities.Lesson

sealed class ScheduleState {
    object Loading : ScheduleState()

    data class Idle(
        val lessons: List<Lesson>,
    ) : ScheduleState()

    data class Error(
        val message: String,
    ) : ScheduleState()

    object ItsSummer: ScheduleState()

    object NoLessons: ScheduleState()
}
