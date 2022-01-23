package epos_next.app.network

import epos_next.app.domain.entities.BigDataObject
import epos_next.app.domain.entities.Lesson
import epos_next.app.domain.exceptions.InvalidAuthException
import epos_next.app.domain.exceptions.InvalidCredentials
import epos_next.app.domain.exceptions.InvalidDataException
import epos_next.app.domain.exceptions.NetworkException
import epos_next.app.lib.Either
import epos_next.app.network.requests.auth.AuthenticateRequest
import epos_next.app.network.responces.auth.AuthenticateResponse
import epos_next.app.network.responces.data.BigDataObjectDto
import epos_next.app.network.responces.data.FetchLessonsResponse
import epos_next.app.network.responces.data.GetDataResponse
import io.github.aakira.napier.Napier
import io.ktor.client.features.*
import io.ktor.client.request.*
import kotlinx.datetime.LocalDate

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

            Napier.i("API reply 200 on $email, $password. Response is $response", tag = "API")

            Either.Right(response)
        } catch (e: ResponseException) {
            val statusCode = e.response.status.value
            if (statusCode == 400) {
                Napier.w("API reply 400 on $email, $password", e, tag = "API")
                Either.Left(InvalidCredentials())
            }
            else {
                Napier.e("API reply $statusCode on $email, $password", e, tag = "API")
                Either.Left(InvalidAuthException())
            }
        } catch (e: Throwable) {
            Napier.e("Network exception on $email, $password", e, tag = "API")
            Napier.e(e.toString(), tag = "API")
            Either.Left(NetworkException())
        }
    }

    override suspend fun getData(): Either<Exception, BigDataObject> {
        return try {
            val response: GetDataResponse = client.get(ApiRoutes.data)
            Napier.d("response.hw = ${response.data.homework}")
            Either.Right(response.data.toDomain())
        } catch (e: ResponseException) {
            val statusCode = e.response.status.value
            Napier.e("API reply $statusCode", e, tag = "API")
            Either.Left(InvalidDataException(e))
        } catch (e: Throwable) {
            Napier.e("Network exception", e, tag = "API")
            Napier.e(e.toString(), tag = "API")
            Either.Left(NetworkException(e))
        }
    }

    override suspend fun fetchLessons(
        from: LocalDate,
        to: LocalDate
    ): Either<Exception, List<Lesson>> {
        return try {
            val route = ApiRoutes.fetchLesson(from.toString(), to.toString())
            val response: FetchLessonsResponse = client.get(route)

            val lessons = response.data.map { it.toDomain() }
            Either.Right(lessons)
        } catch (e: ResponseException) {
            val statusCode = e.response.status.value
            Napier.e("API reply $statusCode", e, tag = "API")
            Either.Left(InvalidDataException(e))
        } catch (e: Throwable) {
            Napier.e("Network exception", e, tag = "API")
            Napier.e(e.toString(), tag = "API")
            Either.Left(NetworkException(e))
        }
    }
}