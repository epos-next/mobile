package epos_next.app.data.homework

import epos_next.app.models.Homework
import eposnext.app.data.HomeworkModel

object HomeworkMapper {

    fun mapDatabase(db: HomeworkModel): Homework = Homework(
        id = db.id,
        lesson = db.lesson,
        content = db.content,
        done = db.done,
        date = db.date,
    )
}