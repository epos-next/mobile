package epos_next.app.network

import epos_next.app.domain.exceptions.InvalidAuthException
import epos_next.app.domain.exceptions.InvalidCredentials
import epos_next.app.domain.exceptions.NetworkException
import epos_next.app.lib.Either
import epos_next.app.network.requests.auth.AuthenticateRequest
import epos_next.app.network.responces.auth.AuthenticateResponse
import io.github.aakira.napier.Napier
import io.ktor.client.features.*
import io.ktor.client.request.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface Api {

    /**
     *  Perform POST /authenticate request to API in order to get personal JWT tokens
     *  @param email that is used to log in to EPOS
     *  @param password that is used to log in to EPOS
     *  @return [AuthenticateResponse] on success login, otherwise [Exception]
     */
    suspend fun authenticate(
        email: String,
        password: String
    ): Either<Exception, AuthenticateResponse>
}

class ApiImpl: Api {

    override suspend fun authenticate(
        email: String,
        password: String
    ): Either<Exception, AuthenticateResponse> {
        return try {
            val response: AuthenticateResponse =
                authClient.post(ApiRoutes.authenticate) {
                    body = AuthenticateRequest(email, password)
                }

            Napier.i("API reply 200 on $email, $password. Response is $response")

            Either.Right(response)
        } catch (e: ResponseException) {
            val statusCode = e.response.status.value
            if (statusCode == 400) {
                Napier.w("API reply 400 on $email, $password", e)
                Either.Left(InvalidCredentials())
            }
            else {
                Napier.e("API reply $statusCode on $email, $password", e)
                Either.Left(InvalidAuthException())
            }
        } catch (e: Throwable) {
            Napier.e("Network exception on $email, $password", e)
            Napier.e(e.toString())
            Either.Left(NetworkException())
        }
    }
}