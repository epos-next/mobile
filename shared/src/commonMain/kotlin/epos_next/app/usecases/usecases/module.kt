package epos_next.app.usecases.usecases

import org.koin.dsl.module

val useCasesModule = module {
    single<IsAuthorizedUseCase> { IsAuthorizedUseCaseImpl() }
    single<UpdateTokenIfNeed> { UpdateTokenIfNeedImpl() }
    single<LoginUseCase> { LoginUseCaseImpl() }
}