package epos_next.app.data.homework

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import epos_next.app.models.Homework
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
        database.lessonQueries.transaction {
            // delete all cache
            database.homeworkQueries.deleteAll()
            Napier.i("deleteAll()", tag = "DB")

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

}