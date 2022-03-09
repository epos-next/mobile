package epos_next.app.network

import epos_next.app.domain.entities.*
import epos_next.app.domain.exceptions.InvalidCredentials
import epos_next.app.domain.exceptions.InvalidDataException
import epos_next.app.domain.exceptions.NetworkException
import epos_next.app.lib.Either
import epos_next.app.network.requests.auth.AuthenticateRequest
import epos_next.app.network.requests.data.CreateAdvertisementRequest
import epos_next.app.network.requests.data.CreateControlWorkRequest
import epos_next.app.network.requests.user.UpdateUserRequest
import epos_next.app.network.responces.auth.AuthenticateResponse
import epos_next.app.network.responces.data.CreateAdvertisementResponse
import epos_next.app.network.responces.data.CreateControlWorkResponse
import epos_next.app.network.responces.data.FetchLessonsResponse
import epos_next.app.network.responces.data.GetDataResponse
import io.github.aakira.napier.Napier
import io.ktor.client.features.*
import io.ktor.client.request.*
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ApiImpl : Api, KoinComponent {

    private val client: NetworkClient by inject()

    override suspend fun authenticate(
        email: String,
        password: String
    ): Either<Exception, AuthenticateResponse> = runApi(
        content = {
            val response: AuthenticateResponse =
                authClient.post(ApiRoutes.authenticate) {
                    body = AuthenticateRequest(email, password)
                }

            Napier.i("API reply 200 on $email, $password. Response is $response", tag = "API")

            Either.Right(response)
        },
        handleResponseException = { e ->
            val statusCode = e.response.status.value
            if (statusCode == 400) {
                Napier.w("API reply 400 on $email, $password", e, tag = "API")
                Either.Left(InvalidCredentials())
            } else null
        }
    )


    override suspend fun getData(): Either<Exception, BigDataObject> = runApi {
        val response: GetDataResponse = client.get(ApiRoutes.data)
        Either.Right(response.data.toDomain())
    }

    override suspend fun fetchLessons(
        from: LocalDate,
        to: LocalDate
    ): Either<Exception, List<Lesson>> = runApi {
        val route = ApiRoutes.fetchLesson(from.toString(), to.toString())
        val response: FetchLessonsResponse = client.get(route)

        val lessons = response.data.map { it.toDomain() }
        Either.Right(lessons)
    }

    override suspend fun completeHomework(id: Long): Either<Exception, Nothing?> = runApi {
        val route = ApiRoutes.completeHomework(id)
        client.put<String>(route)
        Either.Right(null)
    }

    override suspend fun cancelCompleteHomework(id: Long): Either<Exception, Nothing?> = runApi {
        val route = ApiRoutes.cancelCompleteHomework(id)
        client.put<String>(route)
        Either.Right(null)
    }

    override suspend fun createControlWork(controlWork: ControlWork): Either<Throwable, Long> {
        return runApi {
            val body = CreateControlWorkRequest.fromControlWork(controlWork)
            val response: CreateControlWorkResponse = client.post(ApiRoutes.createControlWork, body)
            Either.Right(response.id)
        }
    }

    override suspend fun createAdvertisement(advertisement: Advertisement): Either<Throwable, Long> {
        return runApi {
            val body = CreateAdvertisementRequest.fromAdvertisement(advertisement)
            val response: CreateAdvertisementResponse = client.post(ApiRoutes.createAdvertisement, body)
            Either.Right(response.id)
        }
    }

    override suspend fun updateUser(name: String?, username: String?, dateOfBirth: LocalDateTime?): Either<Throwable, User> {
        return runApi {
            val body = UpdateUserRequest(name, username, dateOfBirth)
            val response: User = client.put(ApiRoutes.updateUser, body)
            Either.Right(response)
        }
    }
}

private suspend fun <T> runApi(
    content: suspend () -> Either<Exception, T>,
): Either<Exception, T> {
    return try {
        val c = content()
        c
    } catch (e: ResponseException) {
        val statusCode = e.response.status.value

//        if (handleUnauthorizedStatus(e)) {
//            try {
//                content()
//            } catch (e: ResponseException) {
//                Napier.e("API reply $statusCode", e, tag = "API")
//                Either.Left(InvalidDataException(e))
//            } catch (e: Throwable) {
//                Napier.e("Network exception", e, tag = "API")
//                Napier.e(e.toString(), tag = "API")
//                Either.Left(NetworkException(e))
//            }
//        }

        Napier.e("API reply $statusCode", e, tag = "API")
        Either.Left(InvalidDataException(e))
    } catch (e: Throwable) {
        Napier.e("Network exception", e, tag = "API")
        Napier.e(e.toString(), tag = "API")
        Either.Left(NetworkException(e))
    }
}

private suspend fun <T> runApi(
    content: suspend () -> Either<Exception, T>,
    handleResponseException: suspend (e: ResponseException) -> Either<Exception, T>? = { null }
): Either<Exception, T> {
    return try {
        content()
    } catch (e: ResponseException) {
        val statusCode = e.response.status.value

//        if (handleUnauthorizedStatus(e)) {
//            try {
//                content()
//            } catch (e: ResponseException) {
//                Napier.e("API reply $statusCode", e, tag = "API")
//                handleResponseException(e) ?: Either.Left(InvalidDataException(e))
//            } catch (e: Throwable) {
//                Napier.e("Network exception", e, tag = "API")
//                Napier.e(e.toString(), tag = "API")
//                Either.Left(NetworkException(e))
//            }
//        }

        Napier.e("API reply $statusCode", e, tag = "API")
        handleResponseException(e) ?: Either.Left(InvalidDataException(e))
    } catch (e: Throwable) {
        Napier.e("Network exception", e, tag = "API")
        Napier.e(e.toString(), tag = "API")
        Either.Left(NetworkException(e))
    }
}