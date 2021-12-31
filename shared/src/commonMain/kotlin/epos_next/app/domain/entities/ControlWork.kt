package epos_next.app.domain.entities

import kotlinx.datetime.LocalDateTime

data class ControlWork(
    val id: Long,
    val lesson: String,
    val date: LocalDateTime,
    val name: String,
)