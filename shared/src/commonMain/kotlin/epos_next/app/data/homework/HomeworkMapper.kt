package epos_next.app.data.homework

import epos_next.app.domain.entities.Homework
import epos_next.db.HomeworkModel
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object HomeworkMapper {

    fun mapDatabase(db: HomeworkModel): Homework = Homework(
        id = db.id,
        lesson = db.lesson,
        content = db.content,
        done = db.done,
        date = db.date,
    )
}