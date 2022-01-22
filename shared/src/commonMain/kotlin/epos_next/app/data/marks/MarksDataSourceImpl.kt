package epos_next.app.data.marks

import epos_next.app.domain.entities.Marks
import epos_next.db.AppDatabase
import io.github.aakira.napier.Napier
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MarksDataSourceImpl: MarksDataSource, KoinComponent {

    private val database: AppDatabase by inject()

    override fun save(marks: Marks) {
        database.lessonMarkQueries.transaction {
            // Delete previously cached marks
            database.lessonMarkQueries.deleteAll()
            Napier.i("deleteAll()", tag = "DB")

            marks.forEach {
                database.lessonMarkQueries.insert(
                    lesson = it.key,
                    periods = it.value.periods,
                    total = it.value.total?.toInt()
                )
                Napier.i("insert($it)", tag = "DB")
            }
        }
    }

    override fun get(): Marks {
        val raw = database.lessonMarkQueries.getAll().executeAsList()
        val marks = MarksMapper.mapDatabase(raw)
        Napier.i("getAll() = $marks", tag = "DB")
        return marks
    }
}