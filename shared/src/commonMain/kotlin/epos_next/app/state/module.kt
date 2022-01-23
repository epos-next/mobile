package epos_next.app.state

import epos_next.app.state.authStatus.AuthStatusReducer
import epos_next.app.state.homework.HomeworkReducer
import epos_next.app.state.schedule.ScheduleReducer
import org.koin.dsl.module

val stateModule = module {
    single { AuthStatusReducer() }
    single { ScheduleReducer() }
    single { HomeworkReducer() }
}