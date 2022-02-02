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

    /**
     * Should used when creating new control work
     * @param controlWork which should be cached
     * @return nothing
     */
    fun cacheOne(controlWork: ControlWork)

    /**
     * Replace fake id with real id. When creating new control work app will used fake id = -1 to
     * cache it. When api return real id of newly created control work, then app should replace
     * fake id of this entity to real id. This is for what this method should be used
     * @param name of control work to identify id of which control work should be replaced if there's
     * more than one control works, that waiting for id
     * @param realId value on which fake id will be replaced
     */
    fun replaceFakeIdWithReal(name: String, realId: Long)

    /**
     * Remove all cached control works
     */
    fun clearAll()
}