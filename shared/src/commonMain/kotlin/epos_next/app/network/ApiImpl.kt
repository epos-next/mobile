package epos_next.app.network

import epos_next.app.domain.entities.BigDataObject
import epos_next.app.domain.exceptions.InvalidAuthException
import epos_next.app.domain.exceptions.InvalidCredentials
import epos_next.app.domain.exceptions.InvalidDataException
import epos_next.app.domain.exceptions.NetworkException
import epos_next.app.lib.Either
import epos_next.app.network.requests.auth.AuthenticateRequest
import epos_next.app.network.responces.auth.AuthenticateResponse
import epos_next.app.network.responces.data.BigDataObjectDto
import io.github.aakira.napier.Napier
import io.ktor.client.features.*
import io.ktor.client.request.*

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

    override suspend fun getData(): Either<Exception, BigDataObject> {
        return try {
            val response: BigDataObjectDto = client.get(ApiRoutes.data)

            Napier.i("API reply 200. Response is $response")

            Either.Right(response.toDomain())
        } catch (e: ResponseException) {
            val statusCode = e.response.status.value
            Napier.e("API reply $statusCode", e)
            Either.Left(InvalidDataException(e))
        } catch (e: Throwable) {
            Napier.e("Network exception", e)
            Napier.e(e.toString())
            Either.Left(NetworkException(e))
        }
    }


}