package epos_next.app.network.responces.data

import kotlinx.serialization.Serializable

@Serializable
data class FetchLessonsResponse(
    val success: Boolean,
    val data: List<LessonDto>
)