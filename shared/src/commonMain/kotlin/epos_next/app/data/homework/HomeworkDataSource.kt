package epos_next.app.data.homework

import epos_next.app.models.Homework
import kotlinx.coroutines.flow.Flow

interface HomeworkDataSource {
    /**
     * Observe homework table and emits new updates
     * only only relevant at the moment of call DZ gets into flow
     * @return flow of [Homework]
     */
    fun get(): Flow<List<Homework>>

    /**
     * Should used to cache homework.
     * Replace all currently saved lessons with [homework].
     * @param homework which should be saved
     * @return nothing
     */
    fun cacheMany(homework: Iterable<Homework>)

    /**
     * Update done property of entity with given id
     * @param id of homework which should be updated
     * @param done value of homework to which entity should be updated
     * @return nothing
     */
    fun updateDone(id: Long, done: Boolean)
}