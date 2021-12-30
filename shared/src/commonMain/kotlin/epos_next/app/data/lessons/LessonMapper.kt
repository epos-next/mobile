package epos_next.app.data.lessons

import epos_next.app.models.Lesson
import eposnext.app.data.Lessons

object LessonMapper {

    fun mapDatabase(db: Lessons): Lesson = Lesson(
        id = db.id,
        subject = db.subject,
        groupId = db.groupId,
        room = db.room,
        date = db.date,
        lessonNumber = db.lessonNumber,
        duration = db.duration,
    )
}