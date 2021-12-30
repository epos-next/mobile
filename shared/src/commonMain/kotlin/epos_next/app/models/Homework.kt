package epos_next.app.models

import kotlinx.datetime.LocalDateTime

data class Homework(
    val id: Long,
    val lesson: String,
    val content: String,
    val done: Boolean,
    val date: LocalDateTime
)
