package epos_next.app.state.advertisements

import epos_next.app.data.advertisement.AdvertisementDataSource
import epos_next.app.domain.exceptions.translateException
import epos_next.app.lib.BaseProxyReducer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.koin.core.component.inject

class AdvertisementsReducer: BaseProxyReducer<AdvertisementsState>(AdvertisementsState.Loading) {
    private val advertisementDataSource: AdvertisementDataSource by inject()

    override val state: Flow<AdvertisementsState> = advertisementDataSource
        .get()
        .catch { e -> AdvertisementsState.Error(translateException(e)) }
        .map {
            if (it.isEmpty()) AdvertisementsState.NoAdvertisements
            else AdvertisementsState.Idle(it)
        }
}