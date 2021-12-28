package epos_next.app.usecases.usecases

import epos_next.app.data.auth.AuthDataStore
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.time.ExperimentalTime

interface UpdateTokenIfNeed {
    @ExperimentalTime
    suspend fun execute()
}

class UpdateTokenIfNeedImpl : UpdateTokenIfNeed, KoinComponent {
    private val authDataStore: AuthDataStore by inject()

    @ExperimentalTime
    override suspend fun execute() {
        authDataStore.shouldUpdateRefreshToken().fold(
            { print(it) },
            {  }
        )
    }
}