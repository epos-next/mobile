package epos_next.app.network.requests.data

import epos_next.app.domain.entities.Advertisement
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class CreateAdvertisementRequest(
    val content: String,
    val targetDate: LocalDateTime
) {
    companion object {
        fun fromAdvertisement(ad: Advertisement): CreateAdvertisementRequest {
            return CreateAdvertisementRequest(
                content = ad.content,
                targetDate = ad.targetDate,
            )
        }
    }
}