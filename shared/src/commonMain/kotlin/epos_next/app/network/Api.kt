package epos_next.app.network

import epos_next.app.domain.entities.BigDataObject
import epos_next.app.domain.entities.Lesson
import epos_next.app.domain.exceptions.InvalidAuthException
import epos_next.app.domain.exceptions.InvalidCredentials
import epos_next.app.domain.exceptions.NetworkException
import epos_next.app.lib.Either
import epos_next.app.network.requests.auth.AuthenticateRequest
import epos_next.app.network.responces.auth.AuthenticateResponse
import io.github.aakira.napier.Napier
import io.ktor.client.features.*
import io.ktor.client.request.*
import kotlinx.datetime.LocalDate
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

    /**
     * The most important api request. Called on app start and fetch everything: user, control works,
     * advertisements, homework, lesson schedule on current week and marks.
     */
    suspend fun getData(): Either<Exception, BigDataObject>

    /**
     * Fetch lesson in range [from]...[to]
     * @param from left range border
     * @param to right range border
     * @return lessons
     */
    suspend fun fetchLessons(from: LocalDate, to: LocalDate): Either<Exception, List<Lesson>>
}

