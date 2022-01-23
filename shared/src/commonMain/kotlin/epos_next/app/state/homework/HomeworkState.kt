package epos_next.app.state.homework

import epos_next.app.domain.entities.Homework


sealed class HomeworkState {
    object Loading : HomeworkState()

    data class Idle(
        val homework: List<Homework>,
    ) : HomeworkState()

    data class Error(
        val message: String,
    ) : HomeworkState()
}
