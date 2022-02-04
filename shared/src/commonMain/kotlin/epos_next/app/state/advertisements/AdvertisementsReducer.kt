package epos_next.app.state.advertisements

import epos_next.app.data.advertisement.AdvertisementDataSource
import epos_next.app.domain.entities.Advertisement
import epos_next.app.domain.exceptions.translateException
import epos_next.app.lib.BaseProxyReducer
import epos_next.app.network.Api
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import org.koin.core.component.inject

class AdvertisementsReducer: BaseProxyReducer<AdvertisementsState>(AdvertisementsState.Loading) {
    private val advertisementDataSource: AdvertisementDataSource by inject()
    private val api: Api by inject()

    override val state: Flow<AdvertisementsState> = advertisementDataSource
        .get()
        .catch { e -> AdvertisementsState.Error(translateException(e)) }
        .map {
            if (it.isEmpty()) AdvertisementsState.NoAdvertisements
            else AdvertisementsState.Idle(it)
        }

    fun createAd(content: String, date: LocalDateTime) = scope.launch {
        try {
            // create new entity
            val ad = Advertisement(id = -1, content = content, targetDate = date)

            // push new entity to cache to show it to user even if it has no id yet
            advertisementDataSource.cacheOne(ad)

            // push entity to api to get id of created entity
            api.createAdvertisement(ad).fold(
                { Napier.e("failed to create advertisement", it, tag = "State") },
                { id ->
                    // replace fake if of entity to real one
                    advertisementDataSource.replaceFakeIdWithReal(content, id)
                }
            )
        } catch (e: Throwable) {
            Napier.e("Failed to create ad", e, tag = "State")
        }
    }
}