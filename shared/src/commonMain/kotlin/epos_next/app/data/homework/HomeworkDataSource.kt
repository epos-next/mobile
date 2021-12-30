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
}