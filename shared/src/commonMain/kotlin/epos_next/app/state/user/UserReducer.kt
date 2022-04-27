package epos_next.app.state.user

import co.touchlab.kermit.Logger
import epos_next.app.data.user.UserDataSource
import epos_next.app.lib.BaseReducer
import epos_next.app.usecases.*
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import org.koin.core.component.inject
import kotlin.time.ExperimentalTime

class UserReducer : BaseReducer<UserState>(UserState.Loading) {
    private val isAuthorized: IsAuthorizedUseCase by inject()
    private val loginUseCase: LoginUseCase by inject()
    private val clearAppAfterLogout: ClearAppAfterLogoutUseCase by inject()
    private val fetchBigDataObject: FetchBigDataObjectUseCase by inject()
    private val userDataSource: UserDataSource by inject()
    private val updateUserUseCase: UpdateUserUseCase by inject()
    private val logger = Logger.withTag("SchoolTestsReducer")

    init {
        try {
            val isAuthorized = isAuthorized.execute()
            stateFlow.update {
                if (isAuthorized) {
                    val user = userDataSource.get()
                    if (user == null) UserState.NotAuthorized
                    else UserState.Authorized(user)
                } else UserState.NotAuthorized
            }
        } catch (e: Throwable) {
            logger.e("error when init UserReducer", e)
        }
    }

    @ExperimentalTime
    suspend fun login(email: String, password: String): Throwable? {
        return loginUseCase.loginWithEmailAndPassword(email, password).fold(
            { it },
            { user ->
                logger.i("success login (user = $user)")
                stateFlow.update { UserState.Authorized(user) }
                scope.launch {
                    fetchBigDataObject()
                }
                null
            }
        )
    }

    suspend fun update(
        name: String? = null,
        username: String? = null,
        dateOfBirth: LocalDateTime? = null,
    ) {
        updateUserUseCase(name, username, dateOfBirth).fold(
            { failure -> print(failure) },
            { user -> stateFlow.update { UserState.Authorized(user) } }
        )

    }

    suspend fun logout() {
        stateFlow.update { UserState.NotAuthorized }
        clearAppAfterLogout()
    }
}