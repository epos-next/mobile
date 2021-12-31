package epos_next.app.data.controlWork

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import epos_next.app.domain.entities.ControlWork
import epos_next.db.AppDatabase
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ControlWorkDataSourceImpl : ControlWorkDataSource, KoinComponent {

    private val database: AppDatabase by inject()

    override fun get(): Flow<List<ControlWork>> {
        return database.controlWorkQueries
            .getFresh()
            .asFlow()
            .mapToList()
            .map { list ->
                list.map { ControlWorkMapper.mapDatabase(it) }
                    .let {
                        Napier.i("getFresh() = $it", tag = "DB")
                        it
                    }
            }
    }

    override fun cacheMany(controlWorks: Iterable<ControlWork>) {
        database.controlWorkQueries.transaction {
            // remove old cached control works
            database.controlWorkQueries.deleteAll()

            // insert new cache
            controlWorks.forEach {
                database.controlWorkQueries.insert(
                    id = it.id,
                    lesson = it.lesson,
                    date = it.date,
                    name = it.name,
                )
            }
        }
    }
}