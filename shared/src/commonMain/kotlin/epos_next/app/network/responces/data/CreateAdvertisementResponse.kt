package epos_next.app.network.responces.data

import kotlinx.serialization.Serializable

@Serializable
data class CreateAdvertisementResponse(
    val success: Boolean,
    val id: Long,
)