package epos_next.app.usecases

import epos_next.app.data.lessons.LessonsDataSource
import epos_next.app.domain.entities.BigDataObject
import epos_next.app.domain.exceptions.translateException
import epos_next.app.network.Api
import epos_next.app.state.schedule.ScheduleReducer
import epos_next.app.state.schedule.ScheduleState
import io.github.aakira.napier.Napier
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface FetchBigDataObjectUseCase {
    /**
     * Will fetch [BigDataObject] and emit data to the corresponding reducer
     */
    suspend operator fun invoke()
}

class FetchBigDataObjectUseCaseImpl : FetchBigDataObjectUseCase, KoinComponent {
    private val api: Api by inject()
    private val scheduleReducer: ScheduleReducer by inject()
    private val lessonsDataSource: LessonsDataSource by inject()

    override suspend fun invoke() {
        try {
            updateReducerWithCached()
//            api.getData().fold(::handleError, ::handleSuccess)
        } catch (e: Exception) {
            Napier.e("failed to fetch BDO", e, tag = "UseCase")
            Napier.e(e.stackTraceToString(), tag = "UseCase")
        }
    }

    // Emit error state to every reducer
    private fun handleError(e: Exception) {
        val message = translateException(e)
        scheduleReducer.setError(message)
    }

    // Emit idle state to every reducer
    private fun handleSuccess(data: BigDataObject) {
        cache(data)
        updateReducerWithCached()
    }

    private fun updateReducerWithCached() {
        scheduleReducer.loadTodaySchedule()
    }

    // cache new data
    private fun cache(data: BigDataObject) {
        lessonsDataSource.cacheMany(data.lessons)
    }

}