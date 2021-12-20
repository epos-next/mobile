package epos_next.app.usecases

import epos_next.app.data.auth.AuthDataStore
import epos_next.app.domain.exceptions.TokenException
import epos_next.app.lib.Either
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import kotlin.time.ExperimentalTime

interface UpdateTokenIfNeed {
    @ExperimentalTime
    suspend fun execute(): Unit
}

internal class UpdateTokenIfNeedImpl(override val di: DI) : UpdateTokenIfNeed, DIAware {
    private val authDataStore: AuthDataStore by instance()

    @ExperimentalTime
    override suspend fun execute(): Unit {
        authDataStore.shouldUpdateRefreshToken().fold(
            { print(it) },
            {  }
        )
    }
}