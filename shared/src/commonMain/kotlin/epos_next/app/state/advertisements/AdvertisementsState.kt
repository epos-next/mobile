package epos_next.app.state.advertisements

import epos_next.app.domain.entities.Advertisement


sealed class AdvertisementsState {
    object Loading : AdvertisementsState()

    data class Idle(
        val advertisements: List<Advertisement>,
    ) : AdvertisementsState()

    data class Error(
        val message: String,
    ) : AdvertisementsState()

    object NoAdvertisements: AdvertisementsState()
}
