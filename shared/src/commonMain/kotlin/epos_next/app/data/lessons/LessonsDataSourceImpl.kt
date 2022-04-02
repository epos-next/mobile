package epos_next.app.data.lessons

import co.touchlab.kermit.Logger
import epos_next.app.domain.entities.Lesson
import epos_next.db.AppDatabase
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class LessonsDataSourceImpl: LessonsDataSource, KoinComponent {

    private val logger = Logger.withTag("LessonsDataSource")
    private val database: AppDatabase by inject()

    override fun cacheMany(lessons: Iterable<Lesson>) {
        val dates = lessons.map { it.date.date }.toSet()
        database.lessonQueries.transaction {
            dates.forEach {
                // dates which will used to find previously cached lessons.
                val from = it.toString()
                val to = it.plus(1, DateTimeUnit.DAY).toString()

                // delete previously cached
                database.lessonQueries.deleteByDate(from, to)
                logger.i { "deleteByDate($from, $to)" }
            }
        }

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
               logger.i { "insert($it)" }
            }
        }
    }

    override fun getByDate(date: LocalDate): List<Lesson>? {
        val from = date.toString()
        val to = date.plus(1, DateTimeUnit.DAY).toString()

        val exists = database.lessonDatesQueries.isCached(date).executeAsOne()
        logger.i { "isCached($date) = $exists" }

        // If nothing is known about this date, null is returned
        if (!exists) return null

        return database.lessonQueries
            .getByDate(from, to)
            .executeAsList()
            .map { LessonMapper.mapDatabase(it) }
            .let {
                logger.i { "getByDate($from, $to) = $it" }
                it
            }
    }

    override fun setCacheMarkers(from: LocalDate, to: LocalDate) {
        database.lessonDatesQueries.transaction {
            var date = from
            while (date <= to) {
                logger.i {"cache($date)" }
                database.lessonDatesQueries.cache(date)
                date = date.plus(1, DateTimeUnit.DAY)
            }
        }
    }

    override fun clearAll() {
        database.lessonQueries.deleteAll()
        database.lessonDatesQueries.removeAll()
    }
}