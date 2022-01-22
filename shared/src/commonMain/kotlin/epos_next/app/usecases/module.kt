package epos_next.app.usecases

import org.koin.dsl.module

val useCasesModule = module {
    single<IsAuthorizedUseCase> { IsAuthorizedUseCaseImpl() }
    single<UpdateTokenIfNeed> { UpdateTokenIfNeedImpl() }
    single<LoginUseCase> { LoginUseCaseImpl() }
    single<FetchBigDataObjectUseCase> { FetchBigDataObjectUseCaseImpl() }
    single<ClearAppAfterLogoutUseCase> { ClearAppAfterLogoutUseCaseImpl() }
}