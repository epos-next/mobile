package epos_next.app

import epos_next.app.data.dataModule
import epos_next.app.network.networkModule
import epos_next.app.usecases.useCasesModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(appModule: Module): KoinApplication =
    startKoin {
        modules(
            appModule,
            dataModule,
            networkModule,
            useCasesModule,
            platformModule,
        )
    }


expect val platformModule: Module