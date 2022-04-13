package epos_next.app.data.controlWork

import epos_next.app.domain.entities.ControlWork
import epos_next.db.ControlWorkModel

object ControlWorkMapper {

    fun mapDatabase(db: ControlWorkModel): ControlWork {
        return ControlWork(
            id = db.id,
            lesson = db.lesson,
            date = db.date,
            name = db.name,
        )
    }
}