package epos_next.app.network.requests.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticateRequest(
    val email: String,
    val password: String,
)
