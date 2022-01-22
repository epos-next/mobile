package epos_next.app.network.responces.data

import kotlinx.serialization.Serializable

@Serializable
data class GetDataResponse(
    val success: Boolean,
    val data: BigDataObjectDto,
)