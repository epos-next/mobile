package epos_next.app.data

import epos_next.app.data.adapters.durationAdapter
import epos_next.app.data.adapters.localDateTimeAdapter
import epos_next.app.data.auth.AuthDataStore
import epos_next.app.data.auth.AuthDataStoreImpl
import epos_next.app.data.lessons.LessonsDataSource
import epos_next.app.data.lessons.LessonsDataSourceImpl
import epos_next.db.AppDatabase
import eposnext.app.data.Lessons
import org.koin.dsl.module

val dataModule = module {
    single<AuthDataStore> { AuthDataStoreImpl() }
    single {
        AppDatabase(
            get(),
            lessonsAdapter = Lessons.Adapter(
                dateAdapter = localDateTimeAdapter,
                durationAdapter = durationAdapter,
            ),
        )
    }

    single<LessonsDataSource> { LessonsDataSourceImpl() }
}