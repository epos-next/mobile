package epos_next.app.usecases

import epos_next.app.data.auth.AuthDataStore
import epos_next.app.domain.exceptions.AuthException
import epos_next.app.domain.exceptions.InvalidAuthException
import epos_next.app.domain.exceptions.InvalidCredentials
import epos_next.app.lib.Either
import epos_next.app.network.ApiRoutes
import epos_next.app.network.NetworkClient
import epos_next.app.network.requests.auth.AuthenticateRequest
import epos_next.app.network.responces.auth.AuthenticateResponse
import io.ktor.client.features.*
import io.ktor.client.request.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.time.ExperimentalTime

interface LoginUseCase {
    @ExperimentalTime
    suspend fun execute(email: String, password: String): Either<AuthException, Boolean>
}

class LoginUseCaseImpl : LoginUseCase, KoinComponent {

    private val networkClient: NetworkClient by inject()
    private val authDataStore: AuthDataStore by inject()

    @ExperimentalTime
    override suspend fun execute(email: String, password: String): Either<AuthException, Boolean> {
        return try {
            val response = networkClient.client.post<AuthenticateResponse>(ApiRoutes.authenticate) {
                body = AuthenticateRequest(email, password)
            }

            authDataStore.setId(response.id)
            authDataStore.setTokens(response.tokens)

            Either.Right(true)
        } catch (e: ResponseException) {
            if (e.response.status.value == 400) Either.Left(InvalidCredentials())
            else Either.Left(InvalidAuthException())
        }
    }

}