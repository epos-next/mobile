package epos_next.app.network.requests.data

import epos_next.app.domain.entities.ControlWork
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class CreateControlWorkRequest(
    val lesson: String,
    val date: LocalDateTime,
    val name: String,
) {
    companion object {
        fun fromControlWork(controlWork: ControlWork): CreateControlWorkRequest {
            return CreateControlWorkRequest(
                lesson = controlWork.lesson,
                date = controlWork.date,
                name = controlWork.name,
            )
        }
    }
}
