package epos_next.app.domain.entities

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class ControlWork(
    val id: Long,
    val lesson: String,
    val date: LocalDateTime,
    val name: String,
)