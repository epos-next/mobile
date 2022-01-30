package epos_next.app.domain.entities

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlin.native.concurrent.SharedImmutable
import kotlin.native.concurrent.ThreadLocal

@Serializable
data class Homework(
    val id: Long,
    val lesson: String,
    val content: String,
    val done: Boolean,
    val date: LocalDateTime
)
