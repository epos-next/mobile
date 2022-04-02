package epos_next.app.state.schedule

import co.touchlab.kermit.Logger
import epos_next.app.data.lessons.LessonsDataSource
import epos_next.app.domain.exceptions.translateException
import epos_next.app.lib.BaseReducer
import epos_next.app.network.Api
import kotlinx.coroutines.flow.update
import kotlinx.datetime.*
import org.koin.core.component.inject

class ScheduleReducer : BaseReducer<ScheduleState>(ScheduleState.Loading) {

    private val lessonsDataSource: LessonsDataSource by inject()
    private val api: Api by inject()
    private val logger = Logger.withTag("ScheduleReducer")

    fun loadTodaySchedule() {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val lessons = lessonsDataSource.getByDate(today)?.sortedBy { it.date }

        when {
            lessons == null -> stateFlow.update { ScheduleState.Loading }
            lessons.isEmpty() -> stateFlow.update { ScheduleState.NoLessons }
            else -> stateFlow.update { ScheduleState.Idle(lessons) }
        }
    }

    suspend fun loadDateSchedule(date: LocalDate): Int {
        loadSchedule(date)

        return state.value.let {
            if (it is ScheduleState.Idle) it.lessons.size
            else 0
        }
    }

    private suspend fun loadSchedule(date: LocalDate) = try {
        val lessons = lessonsDataSource.getByDate(date)?.sortedBy { it.date }

        when {
            lessons == null -> {
                stateFlow.update { ScheduleState.Loading }
                fetchNewLessons(date)
            }
            lessons.isEmpty() -> stateFlow.update { ScheduleState.NoLessons }
            else -> stateFlow.update { ScheduleState.Idle(lessons) }
        }
    } catch (e: Exception) {
        logger.e("failed to load schedule", e)
        logger.e(e.stackTraceToString())

        val message = translateException(e)
        stateFlow.update { ScheduleState.Error(message) }
    }

    private suspend fun fetchNewLessons(date: LocalDate) {
        val from = date.minus(date.dayOfWeek.ordinal, DateTimeUnit.DAY)
        val to = from.plus(6, DateTimeUnit.DAY)

        logger.d("from = $from, to = $to")

        api.fetchLessons(from, to).fold(
            {
                val message = translateException(it)
                setError(message)
            },
            { lessons ->
                // cache lessons
                lessonsDataSource.cacheMany(lessons)

                // for setCacheMarkers
                lessonsDataSource.setCacheMarkers(from, to)
            }
        )
    }

    fun setError(message: String) {
        stateFlow.update { ScheduleState.Error(message) }
    }

    fun clearSchedule() {
        stateFlow.update { ScheduleState.Loading }
    }
}