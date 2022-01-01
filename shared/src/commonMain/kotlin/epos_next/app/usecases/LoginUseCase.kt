package epos_next.app.usecases

import epos_next.app.data.auth.AuthDataStore
import epos_next.app.lib.Either
import epos_next.app.network.Api
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
    suspend fun execute(email: String, password: String): Either<Exception, Int>
}

class LoginUseCaseImpl : LoginUseCase, KoinComponent {

    private val api: Api by inject()
    private val authDataStore: AuthDataStore by inject()

    @ExperimentalTime
    override suspend fun execute(email: String, password: String): Either<Exception, Int> {
        return api.authenticate(email, password).fold(
            { Either.Left(it) },
            {
                authDataStore.setId(it.id)
                authDataStore.setTokens(it.tokens)
                Either.Right(it.id)
            }
        )
    }

}