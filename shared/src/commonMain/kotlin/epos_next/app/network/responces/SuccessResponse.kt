package epos_next.app.network.responces

import kotlinx.serialization.Serializable

@Serializable()
data class SuccessResponse(
    val success: Boolean
)