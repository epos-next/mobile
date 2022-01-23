package epos_next.app.data.lessons

import epos_next.app.domain.entities.Lesson
import kotlinx.datetime.LocalDate
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
     * @return lessons from databases that have the same date as [date].
     * If it is known that there are no lessons on this day, then an empty list is returned
     * If nothing is known about this date, null is returned
     */
    fun getByDate(date: LocalDateTime): List<Lesson>?

    /**
     * Set cache flags on day in range [from]...[to]
     * When requesting lessons for a day with a flag, the app will think that the lessons
     * are cached, even if there are no lessons on that day. If the day is not marked with a flag,
     * then the app will not even try to find the necessary lessons in the cache
     *
     * This flags used in [getByDate]. For example, there are no lessons on Sunday, so the
     * application should return an empty list, not null
     *
     * @param from left range border
     * @param to right range border
     * @return nothing
     */
    fun setCacheMarkers(from: LocalDate, to: LocalDate)
}