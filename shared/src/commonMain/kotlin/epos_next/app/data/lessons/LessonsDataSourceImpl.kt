package epos_next.app.data.lessons

import epos_next.app.models.Lesson
import epos_next.db.AppDatabase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class LessonsDataSourceImpl: LessonsDataSource, KoinComponent {

    private val database: AppDatabase by inject()

    override fun insertMany(lessons: Iterable<Lesson>) {
        database.lessonQueries.transaction {
            lessons.forEach {
                database.lessonQueries.insert(
                    id = it.id,
                    subject = it.subject,
                    groupId = it.groupId,
                    room = it.room,
                    lessonNumber = it.lessonNumber,
                    date = it.date,
                    duration = it.duration,
                )
            }
        }
    }
}