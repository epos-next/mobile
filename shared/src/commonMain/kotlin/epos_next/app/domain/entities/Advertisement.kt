package epos_next.app.domain.entities

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Advertisement(
    val id: Long,
    val content: String,
    val targetDate: LocalDateTime,
)
