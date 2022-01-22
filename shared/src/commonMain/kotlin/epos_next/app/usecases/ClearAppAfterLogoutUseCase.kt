package epos_next.app.usecases

import epos_next.app.data.auth.AuthDataStore
import epos_next.app.state.authStatus.AuthStatusReducer
import epos_next.app.state.schedule.ScheduleReducer
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ClearAppAfterLogoutUseCase {
    /**
     * Clear all user specific data after logout, such is schedule, hw, tests, user object etc.
     */
    suspend operator fun invoke()
}

class ClearAppAfterLogoutUseCaseImpl: ClearAppAfterLogoutUseCase, KoinComponent {
    private val scheduleReducer: ScheduleReducer by inject()
    private val authStatusReducer: AuthStatusReducer by inject()
    private val authDataState: AuthDataStore by inject()

    override suspend fun invoke() {
        clearState()
        clearData()
    }

    private fun clearData() {
        authDataState.clearAll()
    }

    private fun clearState() {
        scheduleReducer.clearSchedule()
    }
}