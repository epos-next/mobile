package epos_next.app.data.homework

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import epos_next.app.domain.entities.Homework
import epos_next.db.AppDatabase
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeworkDataSourceImpl : HomeworkDataSource, KoinComponent {

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
                        Napier.i("getFresh() = $it", tag = "DB")
                        it
                    }
            }
    }

    override fun cacheMany(homework: Iterable<Homework>) {
        // delete all cache
        database.homeworkQueries.deleteAll()
        Napier.i("deleteAll()", tag = "DB")

        database.homeworkQueries.transaction {

            // replace with new
            homework.forEach {
                Napier.i("insert($it)", tag = "DB")
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
        Napier.i("updateDone($id, $done)", tag = "DB")
    }

    override fun clearAll() {
        database.homeworkQueries.deleteAll()
    }
}