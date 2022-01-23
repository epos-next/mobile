package epos_next.app.state

import epos_next.app.state.advertisements.AdvertisementsReducer
import epos_next.app.state.authStatus.AuthStatusReducer
import epos_next.app.state.homework.HomeworkReducer
import epos_next.app.state.nextLesson.NextLessonReducer
import epos_next.app.state.schedule.ScheduleReducer
import epos_next.app.state.schoolTests.SchoolTestsReducer
import org.koin.dsl.module

val stateModule = module {
    single { AuthStatusReducer() }
    single { ScheduleReducer() }
    single { HomeworkReducer() }
    single { AdvertisementsReducer() }
    single { SchoolTestsReducer() }
    single { NextLessonReducer() }
}