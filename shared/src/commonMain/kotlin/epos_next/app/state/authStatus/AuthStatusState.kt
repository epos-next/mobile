package epos_next.app.state.authStatus

import epos_next.app.domain.entities.User

sealed class AuthStatusState {
    object Loading : AuthStatusState()

    data class Authorized(
        val id: Int
    ) : AuthStatusState()

    object NotAuthorized : AuthStatusState()
}
