package epos_next.app.data.lessons

import epos_next.app.models.Lesson
import epos_next.db.AppDatabase
import io.github.aakira.napier.Napier
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.plus
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class LessonsDataSourceImpl: LessonsDataSource, KoinComponent {

    private val database: AppDatabase by inject()

    override fun insertMany(lessons: Iterable<Lesson>) {
        database.lessonQueries.transaction {
            lessons.forEach {
                Napier.i("insert($it)", tag = "DB")
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

    override fun getByDate(date: LocalDateTime): List<Lesson> {
        val from = date.date.toString()
        val to = date.date.plus(1, DateTimeUnit.DAY).toString()

        return database.lessonQueries
            .getByDate(from, to)
            .executeAsList()
            .map { LessonMapper.mapDatabase(it) }
            .let {
                Napier.i("getByDate($from, $to) = $it", tag = "DB")
                it
            }
    }
}