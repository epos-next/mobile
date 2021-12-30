package epos_next.app.data.lessons

import epos_next.app.models.Lesson
import kotlinx.datetime.LocalDateTime

interface LessonsDataSource {
    /**
     * Should used to cache lessons.
     * Replace all currently saved lessons with [lessons].
     * Used date of first element at [lessons] to find previously cached lessons.
     * @param lessons which should be saved
     * @return nothing
     */
    fun cacheMany(lessons: Iterable<Lesson>)

    /**
     * Grab all lessons from database where date is the same as provided
     * Time is ignored
     * @param date on which lessons will be received
     * @return lessons from databases that have the same date as [date]
     */
    fun getByDate(date: LocalDateTime): List<Lesson>
}