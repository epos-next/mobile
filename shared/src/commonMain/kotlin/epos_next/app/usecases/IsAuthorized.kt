package epos_next.app.usecases

import epos_next.app.data.auth.AuthDataStore
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

interface IsAuthorizedUseCase {
    fun execute(): Boolean
}

internal class IsAuthorizedUseCaseImpl(override val di: DI): IsAuthorizedUseCase, DIAware {
    private val authDataStore: AuthDataStore by instance()

    override fun execute(): Boolean = authDataStore.isAuthorized()
}