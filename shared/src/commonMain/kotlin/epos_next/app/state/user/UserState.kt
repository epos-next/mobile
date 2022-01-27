package epos_next.app.state.user

import epos_next.app.domain.entities.User

sealed class UserState {
    object Loading : UserState()

    data class Authorized(
        val user: User,
    ) : UserState()

    object NotAuthorized : UserState()
}
