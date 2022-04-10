package epos_next.app.domain.entities

import kotlinx.datetime.LocalDateTime
import kotlin.time.Duration

data class Lesson(
    val id: Long,
    val subject: String,
    val groupId: Long,
    val room: String,
    val date: LocalDateTime,
    val lessonNumber: Long,
    val duration: Duration,
    val marks: List<Float>,
    val truancy: Boolean,
)
