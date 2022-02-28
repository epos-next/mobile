package epos_next.app.usecases

import epos_next.app.data.user.UserDataSource
import epos_next.app.domain.entities.User
import epos_next.app.lib.Either
import epos_next.app.network.Api
import kotlinx.datetime.LocalDateTime
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface UpdateUserUseCase {

    /**
     * Updates user with giving data. At least one parameter should be passed
     * @param name displaying user name
     * @param username user username (like @zotov). With out '@' symbol
     * @param dateOfBirth user birthday. Should be < now.
     * @return
     */
    suspend operator fun invoke(
        name: String? = null,
        username: String? = null,
        dateOfBirth: LocalDateTime? = null
    ): Either<Throwable, User>
}

internal class UpdateUserUseCaseImpl: UpdateUserUseCase, KoinComponent {
    private val api: Api by inject()
    private val userDataSource: UserDataSource by inject()

    override suspend fun invoke(
        name: String?,
        username: String?,
        dateOfBirth: LocalDateTime?
    ): Either<Throwable, User> {
        return api.updateUser(name, username, dateOfBirth).fold(
            { Either.Left(it) },
            { user ->
                userDataSource.save(user)
                Either.Right(user)
            }
        )
    }
}
