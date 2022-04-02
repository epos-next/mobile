package epos_next.app.data.homework

import co.touchlab.kermit.Logger
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import epos_next.app.domain.entities.Homework
import epos_next.db.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeworkDataSourceImpl : HomeworkDataSource, KoinComponent {

    private val logger = Logger.withTag("HomeworkDataSource")
    private val database: AppDatabase by inject()

    override fun get(): Flow<List<Homework>> {
        return database.homeworkQueries
            .getFresh()
            .asFlow()
            .mapToList()
            .map { list ->
                list
                    .map { HomeworkMapper.mapDatabase(it) }
                    .let {
                        logger.i{"getFresh() = $it"}
                        it
                    }
            }
    }

    override fun cacheMany(homework: Iterable<Homework>) {
        // delete all cache
        database.homeworkQueries.deleteAll()
        logger.i { "deleteAll()" }

        database.homeworkQueries.transaction {

            // replace with new
            homework.forEach {
                logger.i { "insert($it)" }
                database.homeworkQueries.insert(
                    id = it.id,
                    lesson = it.lesson,
                    content = it.content,
                    done = it.done,
                    date = it.date,
                )
            }
        }
    }

    override fun updateDone(id: Long, done: Boolean) {
        database.homeworkQueries.updateDone(id = id, done = done)
        logger.i {"updateDone($id, $done)" }
    }

    override fun clearAll() {
        database.homeworkQueries.deleteAll()
    }
}