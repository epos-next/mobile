package epos_next.app.network

import epos_next.app.domain.entities.*
import epos_next.app.lib.Either
import epos_next.app.models.VkCookies
import epos_next.app.network.responces.auth.AuthenticateResponse
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

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

    /**
     * Mark homework with [id] as completed
     * @param id homework id
     * @return nothing on success, otherwise [Exception]
     */
    suspend fun completeHomework(id: Long): Either<Exception, Nothing?>

    /**
     * Mark homework with [id] as not completed
     * @param id homework id
     * @return nothing on success, otherwise [Exception]
     */
    suspend fun cancelCompleteHomework(id: Long): Either<Exception, Nothing?>

    /**
     * Creates new control work
     * @param controlWork entity, which represents new control work
     * @return id of created control work on success, otherwise [Throwable]
     */
    suspend fun createControlWork(controlWork: ControlWork): Either<Throwable, Long>

    /**
     * Creates new advertisement
     * @param advertisement entity, which represents new advertisement
     * @return id of created advertisement on success, otherwise [Throwable]
     */
    suspend fun createAdvertisement(advertisement: Advertisement): Either<Throwable, Long>

    /**
     * Updates user with giving data. At least one parameter should be passed
     * @param name displaying user name
     * @param username user username (like @zotov). With out '@' symbol
     * @param dateOfBirth user birthday. Should be < now.
     * @return updated user on success or throwable error
     */
    suspend fun updateUser(
        name: String? = null,
        username: String? = null,
        dateOfBirth: LocalDateTime? = null
    ): Either<Throwable, User>

    /**
     * Check current app version
     * @param versionId the app versionCode (gradle buildscript)
     * @return information about current version (up to date or there is new updates)
     */
    suspend fun getVersionUrgency(versionId: Int): Either<Throwable, AppVersion>

    /**
     * Perform `POST /authenticate-with-vk` request to API in order to get personal JWT tokens
     * @param vkCookies cookies for login.vk.com, oauth.vk.com and vk.com
     * @return Either: [AuthenticateResponse] if success, otherwise [Throwable]
     */
    suspend fun authenticateWithVk(vkCookies: VkCookies): Either<Throwable, AuthenticateResponse>
}

