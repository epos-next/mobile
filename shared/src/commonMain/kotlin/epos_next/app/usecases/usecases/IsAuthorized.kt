package epos_next.app.usecases.usecases

import epos_next.app.data.auth.AuthDataStore
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface IsAuthorizedUseCase {
    fun execute(): Boolean
}

class IsAuthorizedUseCaseImpl: IsAuthorizedUseCase, KoinComponent {
    private val authDataStore: AuthDataStore by inject()

    override fun execute(): Boolean {
       return try {
           authDataStore.isAuthorized()
       } catch (e: Throwable) {
           println(e)
           false
       }
    }
}