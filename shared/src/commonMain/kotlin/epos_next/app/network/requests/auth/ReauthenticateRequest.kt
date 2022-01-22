package epos_next.app.network.requests.auth

import kotlinx.serialization.Serializable

@Serializable
data class ReauthenticateRequest(
    val refresh: String,
    val id: Int,
)
