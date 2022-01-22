package epos_next.app.domain.entities

data class BigDataObject(
    val user: User,
    val lessons: List<Lesson>,
    val homework: List<Homework>,
    val controlWorks: List<ControlWork>,
    val advertisements: List<Advertisement>,
    val marks: Marks,
)