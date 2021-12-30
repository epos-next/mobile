package epos_next.app.data.lessons

import epos_next.app.models.Lesson
import kotlinx.datetime.LocalDateTime

interface LessonsDataSource {
    /**
     * Insert list of lessons to `Lessons` table
     * @param lessons which should be saved
     * @return nothing
     */
    fun insertMany(lessons: Iterable<Lesson>)

    /**
     * Grab all lessons from database where date is the same as provided
     * Time is ignored
     * @param date on which lessons will be received
     * @return lessons from databases that have the same date as [date]
     */
    fun getByDate(date: LocalDateTime): List<Lesson>
}