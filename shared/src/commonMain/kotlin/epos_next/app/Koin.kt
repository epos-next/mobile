package epos_next.app

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.core.module.Module

fun initKoin(appModule: Module): KoinApplication =
    startKoin {
        modules(
            appModule,
            coreModule,
            platformModule,
        )
    }


private val coreModule = module {

}

expect val platformModule: Module