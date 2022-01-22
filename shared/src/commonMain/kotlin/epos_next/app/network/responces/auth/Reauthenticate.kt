package epos_next.app.network.responces.auth

import epos_next.app.models.AuthTokens
import kotlinx.serialization.Serializable

@Serializable
data class ReauthenticateResponse(
    val success: Boolean,
    val tokens: AuthTokens,
    val id: Int
)