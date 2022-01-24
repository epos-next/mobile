package epos_next.app.data

import epos_next.app.data.adapters.durationAdapter
import epos_next.app.data.adapters.localDateAdapter
import epos_next.app.data.adapters.localDateTimeAdapter
import epos_next.app.data.advertisement.AdvertisementDataSource
import epos_next.app.data.advertisement.AdvertisementDataSourceImpl
import epos_next.app.data.auth.AuthDataStore
import epos_next.app.data.auth.AuthDataStoreImpl
import epos_next.app.data.controlWork.ControlWorkDataSource
import epos_next.app.data.controlWork.ControlWorkDataSourceImpl
import epos_next.app.data.homework.HomeworkDataSource
import epos_next.app.data.homework.HomeworkDataSourceImpl
import epos_next.app.data.lessons.LessonsDataSource
import epos_next.app.data.lessons.LessonsDataSourceImpl
import epos_next.app.data.marks.MarksDataSource
import epos_next.app.data.marks.MarksDataSourceImpl
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
            ),
            lessonsDatesAdapter = LessonsDates.Adapter(
                dateAdapter = localDateAdapter,
            )
        )
    }

    single<LessonsDataSource> { LessonsDataSourceImpl() }
    single<HomeworkDataSource> { HomeworkDataSourceImpl() }
    single<ControlWorkDataSource> { ControlWorkDataSourceImpl() }
    single<AdvertisementDataSource> { AdvertisementDataSourceImpl() }
    single<MarksDataSource> { MarksDataSourceImpl() }
}