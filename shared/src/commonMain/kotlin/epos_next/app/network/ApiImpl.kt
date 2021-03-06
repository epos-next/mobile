package epos_next.app.network

import co.touchlab.kermit.Logger
import epos_next.app.domain.entities.*
import epos_next.app.domain.exceptions.InvalidCredentials
import epos_next.app.domain.exceptions.InvalidDataException
import epos_next.app.domain.exceptions.NetworkException
import epos_next.app.lib.Either
import epos_next.app.network.requests.auth.AuthenticateRequest
import epos_next.app.network.requests.data.CreateAdvertisementRequest
import epos_next.app.network.requests.data.CreateControlWorkRequest
import epos_next.app.network.requests.user.UpdateUserRequest
import epos_next.app.network.responces.AppVersionDtoResource
import epos_next.app.network.responces.auth.AuthenticateResponse
import epos_next.app.network.responces.data.CreateAdvertisementResponse
import epos_next.app.network.responces.data.CreateControlWorkResponse
import epos_next.app.network.responces.data.FetchLessonsResponse
import epos_next.app.network.responces.data.GetDataResponse
import epos_next.app.network.responces.parseAppVersionDto
import io.ktor.client.features.*
import io.ktor.client.request.*
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerializationException
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ApiImpl : Api, KoinComponent {

    private val logger = Logger.withTag("API")
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

            logger.i("API reply 200 on $email, $password. Response is $response")

            Either.Right(response)
        },
        handleResponseException = { e ->
            val statusCode = e.response.status.value
            if (statusCode == 400) {
                logger.w("API reply 400 on $email, $password", e)
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

    override suspend fun getVersionUrgency(versionId: Int): Either<Throwable, AppVersion> = runApi {
        val route = ApiRoutes.appVersion(versionId)
        val response: AppVersionDtoResource = client.get(route)
        Either.Right(parseAppVersionDto(response))
    }
}

private suspend fun <T> runApi(
    content: suspend () -> Either<Exception, T>,
): Either<Exception, T> {
    return try {
        content()
    } catch (e: ResponseException) {
        val statusCode = e.response.status.value

        if (handleUnauthorizedStatus(e) != null) {
            return try {
                content()
            } catch (e: ResponseException) {
                Logger.e("API reply $statusCode", e)
                Either.Left(InvalidDataException(e))
            } catch (e: Throwable) {
                Logger.e("Network exception", e)
                Logger.e(e.toString())
                Either.Left(NetworkException(e))
            }
        }

        Logger.e("API reply $statusCode", e)
        return Either.Left(InvalidDataException(e))
    } catch (e: SerializationException) {
        Logger.e("Failed to serialize", e)
        return Either.Left(e)
    }
    catch (e: Throwable) {
        Logger.e("Network exception", e)
        Logger.e(e.toString())
        return Either.Left(NetworkException(e))
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

        if (handleUnauthorizedStatus(e) != null) {
            try {
                content()
            } catch (e: ResponseException) {
                Logger.e("API reply $statusCode", e)
                handleResponseException(e) ?: Either.Left(InvalidDataException(e))
            } catch (e: Throwable) {
                Logger.e("Network exception", e)
                Logger.e(e.toString())
                Either.Left(NetworkException(e))
            }
        }

        Logger.e("API reply $statusCode", e)
        handleResponseException(e) ?: Either.Left(InvalidDataException(e))
    } catch (e: Throwable) {
        Logger.e("Network exception", e)
        Logger.e(e.toString())
        Either.Left(NetworkException(e))
    }
}