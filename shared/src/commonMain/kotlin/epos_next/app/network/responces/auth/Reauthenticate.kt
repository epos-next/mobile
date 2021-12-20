package epos_next.app.network.responces.auth

import epos_next.app.models.AuthTokens

data class ReauthenticateResponse(
    val success: Boolean,
    val tokens: AuthTokens?,
    val id: Int?
)