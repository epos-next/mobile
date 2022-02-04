package epos_next.app.data.advertisement

import epos_next.app.domain.entities.Advertisement
import epos_next.app.domain.entities.ControlWork
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

    /**
     * Should used when creating new advertisement
     * @param advertisement which should be cached
     * @return nothing
     */
    fun cacheOne(advertisement: Advertisement)

    /**
     * Replace fake id with real id. When creating new advertisement app will use fake id = -1 to
     * cache it. When api return real id of newly created advertisement, then app should replace
     * fake id of this entity to real id. This is for what this method should be used
     * @param content of advertisement to identify id of which advertisement should be replaced if there's
     * more than one advertisements, that waiting for id
     * @param realId value on which fake id will be replaced
     */
    fun replaceFakeIdWithReal(content: String, realId: Long)

    /**
     * Remove all cached advertisements
     */
    fun clearAll()
}