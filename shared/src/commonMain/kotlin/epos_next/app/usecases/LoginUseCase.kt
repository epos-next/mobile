package epos_next.app.usecases

import epos_next.app.data.auth.AuthDataStore
import epos_next.app.data.user.UserDataSource
import epos_next.app.domain.entities.User
import epos_next.app.lib.Either
import epos_next.app.network.Api
import epos_next.app.network.responces.auth.AuthenticateResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.time.ExperimentalTime

interface LoginUseCase {
    /**
     * Login user by email and password
     *  @param email that is used to log in to EPOS
     *  @param password that is used to log in to EPOS
     *  @return user id on success login, otherwise [Exception]
     */
    @ExperimentalTime
    suspend fun loginWithEmailAndPassword(email: String, password: String): Either<Exception, User>
}

class LoginUseCaseImpl : LoginUseCase, KoinComponent {

    private val api: Api by inject()
    private val authDataStore: AuthDataStore by inject()
    private val userDataSource: UserDataSource by inject()

    @ExperimentalTime
    override suspend fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): Either<Exception, User> {
        return api.authenticate(email, password).fold(
            { Either.Left(it) },
            ::handleSuccess
        )
    }

    private fun handleSuccess(response: AuthenticateResponse): Either<Exception, User> {
        authDataStore.setId(response.user.id)
        authDataStore.setTokens(response.tokens)
        userDataSource.save(response.user)
        return Either.Right(response.user)
    }
}