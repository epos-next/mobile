package epos_next.app.network.responces.data

import epos_next.app.domain.entities.Lesson
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Serializable
data class LessonDto(
    val id: Long,
    val subject: String,
    val groupId: Long,
    val room: String,
    val date: LocalDateTime,
    val lessonNumber: Long,
    val duration: Int,
    val marks: List<Float>,
) {
    fun toDomain(): Lesson {
        return Lesson(
            id = id,
            subject = subject,
            groupId = groupId,
            room = room,
            date = date,
            lessonNumber = lessonNumber,
            duration = duration.toDuration(DurationUnit.MINUTES),
            marks = marks,
        )
    }
}