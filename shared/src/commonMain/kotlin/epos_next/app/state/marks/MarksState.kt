package epos_next.app.state.marks

import epos_next.app.domain.entities.Marks

sealed class MarksState {
    object Loading : MarksState()

    data class Idle(val marks: Marks) : MarksState()

    data class Error(val message: String) : MarksState()
}