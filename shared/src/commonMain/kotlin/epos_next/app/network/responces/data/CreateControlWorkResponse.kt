package epos_next.app.network.responces.data

import kotlinx.serialization.Serializable

@Serializable
data class CreateControlWorkResponse(
    val success: Boolean,
    val id: Long,
)
