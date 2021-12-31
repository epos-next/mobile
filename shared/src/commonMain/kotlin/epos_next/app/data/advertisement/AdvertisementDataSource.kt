package epos_next.app.data.advertisement

import epos_next.app.domain.entities.Advertisement
import kotlinx.coroutines.flow.Flow

interface AdvertisementDataSource {
    /**
     * Observe advertisements table and emits new updates.
     * only only relevant at the moment of call records gets into flow
     * @return flow of [Advertisement]
     */
    fun get(): Flow<List<Advertisement>>

    /**
     * Should used to cache advertisements.
     * Replace all currently saved advertisements with [advertisements]
     * @param advertisements which should be saved
     * @return nothing
     */
    fun cacheMany(advertisements: Iterable<Advertisement>)
}