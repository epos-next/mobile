package epos_next.app.data

import epos_next.app.data.adapters.durationAdapter
import epos_next.app.data.adapters.localDateTimeAdapter
import epos_next.app.data.auth.AuthDataStore
import epos_next.app.data.auth.AuthDataStoreImpl
import epos_next.app.data.lessons.LessonsDataSource
import epos_next.app.data.lessons.LessonsDataSourceImpl
import epos_next.db.AppDatabase
import eposnext.app.data.HomeworkModel
import eposnext.app.data.LessonModel
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
            )
        )
    }

    single<LessonsDataSource> { LessonsDataSourceImpl() }
}