package epos_next.app.state.authStatus

import epos_next.app.lib.BaseReducer
import epos_next.app.usecases.IsAuthorizedUseCase
import kotlinx.coroutines.flow.update
import org.koin.core.component.inject

class AuthStatusReducer: BaseReducer<AuthStatusState>(AuthStatusState.Loading) {
    private val isAuthorizedUseCase: IsAuthorizedUseCase by inject()

    init {
        val isAuthorized = isAuthorizedUseCase.execute()
        stateFlow.update {
            if (isAuthorized) AuthStatusState.Authorized
            else AuthStatusState.NotAuthorized
        }
    }
}