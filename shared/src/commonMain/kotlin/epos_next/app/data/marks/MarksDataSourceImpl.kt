package epos_next.app.data.marks

import co.touchlab.kermit.Logger
import epos_next.app.domain.entities.Marks
import epos_next.db.AppDatabase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MarksDataSourceImpl: MarksDataSource, KoinComponent {

    private val logger = Logger.withTag("LessonsDataSource")
    private val database: AppDatabase by inject()

    override fun save(marks: Marks) {
        database.lessonMarkQueries.transaction {
            // Delete previously cached marks
            database.lessonMarkQueries.deleteAll()
            logger.i { "deleteAll()" }

            marks.forEach {
                logger.i { "insert($it)" }
                database.lessonMarkQueries.insert(
                    lesson = it.key,
                    periods = it.value.periods,
                    total = it.value.total?.toInt()
                )
            }
        }
    }

    override fun get(): Marks {
        val raw = database.lessonMarkQueries.getAll().executeAsList()
        val marks = MarksMapper.mapDatabase(raw)
        logger.i { "getAll() = $marks" }
        return marks
    }
}