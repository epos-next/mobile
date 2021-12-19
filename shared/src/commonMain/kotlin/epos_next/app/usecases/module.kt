package epos_next.app.usecases

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val useCasesModule = DI.Module("UseCase") {
    bind<IsAuthorizedUseCase>() with singleton { IsAuthorizedUseCaseImpl(di) }
}