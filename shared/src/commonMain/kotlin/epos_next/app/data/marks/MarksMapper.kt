package epos_next.app.data.marks

import epos_next.app.domain.entities.MarkUnit
import epos_next.app.domain.entities.Marks
import epos_next.db.LessonMarkModel

object MarksMapper {

    fun mapDatabase(db: List<LessonMarkModel>): Marks {
        return db.map { it.lesson to MarkUnit(it.periods, it.total?.toDouble()) }.toMap()
    }
}