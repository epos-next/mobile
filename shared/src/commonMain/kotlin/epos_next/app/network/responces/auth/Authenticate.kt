package epos_next.app.network.responces.auth

import epos_next.app.domain.entities.User
import epos_next.app.models.SetAuthTokens
import kotlinx.serialization.Serializable

@Serializable
data class AuthenticateResponse(
    val success: Boolean,
    val tokens: SetAuthTokens,
    val user: User,
)