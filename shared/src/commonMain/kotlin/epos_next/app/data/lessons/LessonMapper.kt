package epos_next.app.data.lessons

import epos_next.app.domain.entities.Lesson
import eposnext.app.data.LessonModel

object LessonMapper {

    fun mapDatabase(db: LessonModel): Lesson = Lesson(
        id = db.id,
        subject = db.subject,
        groupId = db.groupId,
        room = db.room,
        date = db.date,
        lessonNumber = db.lessonNumber,
        duration = db.duration,
    )
}