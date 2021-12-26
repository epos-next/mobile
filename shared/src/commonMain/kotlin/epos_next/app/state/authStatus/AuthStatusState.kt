package epos_next.app.state.authStatus

sealed class AuthStatusState {
    object Loading: AuthStatusState()
    object Authorized: AuthStatusState()
    object NotAuthorized: AuthStatusState()
}
