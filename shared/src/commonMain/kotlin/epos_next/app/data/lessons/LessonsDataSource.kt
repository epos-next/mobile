package epos_next.app.data.lessons

import epos_next.app.models.Lesson

interface LessonsDataSource {
    /**
     * Insert list of lessons to `Lessons` table
     * @param lessons which should be saved
     * @return nothing
     */
    fun insertMany(lessons: Iterable<Lesson>)
}