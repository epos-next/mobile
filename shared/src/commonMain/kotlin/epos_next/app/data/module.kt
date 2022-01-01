package epos_next.app.data

import epos_next.app.data.adapters.durationAdapter
import epos_next.app.data.adapters.localDateTimeAdapter
import epos_next.app.data.auth.AuthDataStore
import epos_next.app.data.auth.AuthDataStoreImpl
import epos_next.app.data.lessons.LessonsDataSource
import epos_next.app.data.lessons.LessonsDataSourceImpl
import epos_next.app.data.marks.marksAdapter
import epos_next.db.AppDatabase
import eposnext.app.data.*
import org.koin.dsl.module

val dataModule = module {
    single<AuthDataStore> { AuthDataStoreImpl() }
    single {
        AppDatabase(
            get(),
            lessonModelAdapter = LessonModel.Adapter(
                dateAdapter = localDateTimeAdapter,
                durationAdapter = durationAdapter,
            ),
            homeworkModelAdapter = HomeworkModel.Adapter(
                dateAdapter = localDateTimeAdapter,
            ),
            controlWorkModelAdapter = ControlWorkModel.Adapter(
                dateAdapter = localDateTimeAdapter,
            ),
            advertisementModelAdapter = AdvertisementModel.Adapter(
                targetDateAdapter = localDateTimeAdapter,
            ),
            lessonMarkModelAdapter = LessonMarkModel.Adapter(
                periodsAdapter = marksAdapter,
            )
        )
    }

    single<LessonsDataSource> { LessonsDataSourceImpl() }
}