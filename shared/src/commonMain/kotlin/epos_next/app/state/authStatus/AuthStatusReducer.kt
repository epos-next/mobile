package epos_next.app.state.authStatus

import epos_next.app.data.auth.AuthDataStore
import epos_next.app.lib.BaseReducer
import epos_next.app.usecases.usecases.IsAuthorizedUseCase
import epos_next.app.usecases.usecases.LoginUseCase
import kotlinx.coroutines.flow.update
import org.koin.core.component.inject
import kotlin.time.ExperimentalTime

class AuthStatusReducer: BaseReducer<AuthStatusState>(AuthStatusState.Loading) {
    private val isAuthorizedUseCase: IsAuthorizedUseCase by inject()
    private val loginUseCase: LoginUseCase by inject()
    private val authDataState: AuthDataStore by inject()

    init {
        val isAuthorized = isAuthorizedUseCase.execute()
        stateFlow.update {
            if (isAuthorized) AuthStatusState.Authorized(id = 1) // TODO: use real id, cached in db
            else AuthStatusState.NotAuthorized
        }
    }

    @ExperimentalTime
    suspend fun login(email: String, password: String): Exception? {
        return loginUseCase.execute(email, password).fold(
            {
                println(it)
                it
            },
            { id ->
                println("success login (id = $id)")
                stateFlow.update {
                    AuthStatusState.Authorized(id = id)
                }
                null
            }
        )
    }

    fun logout() {
        authDataState.clearAll()
        stateFlow.update { AuthStatusState.NotAuthorized }
    }
}