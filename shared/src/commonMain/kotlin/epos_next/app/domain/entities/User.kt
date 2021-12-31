package epos_next.app.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String,
)
