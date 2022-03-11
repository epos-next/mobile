package epos_next.app.state

import epos_next.app.state.advertisements.AdvertisementsReducer
import epos_next.app.state.dark_mode.DarkModeReducer
import epos_next.app.state.user.UserReducer
import epos_next.app.state.homework.HomeworkReducer
import epos_next.app.state.marks.MarksReducer
import epos_next.app.state.nextLesson.NextLessonReducer
import epos_next.app.state.schedule.ScheduleReducer
import epos_next.app.state.schoolTests.SchoolTestsReducer
import org.koin.dsl.module

val stateModule = module {
    single { UserReducer() }
    single { ScheduleReducer() }
    single { HomeworkReducer() }
    single { AdvertisementsReducer() }
    single { SchoolTestsReducer() }
    single { NextLessonReducer() }
    single { MarksReducer() }
    single { DarkModeReducer() }
}