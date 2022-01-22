package epos_next.app.network.responces.data

import epos_next.app.domain.entities.*
import kotlinx.serialization.Serializable

@Serializable
data class BigDataObjectDto(
    val user: User,
    val lessons: List<LessonDto>,
    val homework: List<Homework>,
    val controlWorks: List<ControlWork>,
    val advertisements: List<Advertisement>,
    val marks: Marks,
) {
    fun toDomain(): BigDataObject {
        return BigDataObject(
            user = user,
            lessons = lessons.map { it.toDomain() },
            homework = homework,
            controlWorks = controlWorks,
            advertisements = advertisements,
            marks = marks,
        )
    }
}
