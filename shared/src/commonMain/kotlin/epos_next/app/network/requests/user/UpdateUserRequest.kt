package epos_next.app.network.requests.user

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRequest(
    val name: String?,
    val username: String?,
    val dateOfBirth: LocalDateTime?,
)
