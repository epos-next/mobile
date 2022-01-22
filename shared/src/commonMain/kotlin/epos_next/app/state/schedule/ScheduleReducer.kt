package epos_next.app.state.schedule

import epos_next.app.data.lessons.LessonsDataSource
import epos_next.app.lib.BaseReducer
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.core.component.inject

class ScheduleReducer : BaseReducer<ScheduleState>(ScheduleState.Loading) {

    private val lessonsDataSource: LessonsDataSource by inject()

    fun loadTodaySchedule() {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        loadSchedule(today)
    }

    private fun loadSchedule(date: LocalDateTime) = try {
        val lessons = lessonsDataSource.getByDate(date).sortedBy { it.date }
        stateFlow.update { ScheduleState.Idle(lessons) }
    } catch (e: Exception) {
        Napier.e("failed to load schedule", e, tag = "Reducer")
        Napier.e(e.stackTraceToString(), tag = "Reducer")
    }

    fun setError(message: String) {
        stateFlow.update { ScheduleState.Error(message) }
    }

    fun clearSchedule() {
        stateFlow.update { ScheduleState.Loading }
    }
}