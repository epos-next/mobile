package epos_next.app.state.schoolTests

import epos_next.app.domain.entities.ControlWork


sealed class SchoolTestsState {
    object Loading : SchoolTestsState()

    data class Idle(
        val tests: List<ControlWork>,
    ) : SchoolTestsState()

    data class Error(
        val message: String,
    ) : SchoolTestsState()

    object NoSchoolTests: SchoolTestsState()
}
