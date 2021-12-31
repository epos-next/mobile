package epos_next.app.data.controlWork

import epos_next.app.domain.entities.ControlWork
import kotlinx.coroutines.flow.Flow

interface ControlWorkDataSource {
    /**
     * Observe control work table and emits new updates
     * only only relevant at the moment of call tests gets into flow
     * @return flow of [ControlWork]
     */
    fun get(): Flow<List<ControlWork>>

    /**
     * Should used to cache control works.
     * Replace all currently saved lessons with [controlWorks].
     * @param controlWorks which should be saved
     * @return nothing
     */
    fun cacheMany(controlWorks: Iterable<ControlWork>)
}