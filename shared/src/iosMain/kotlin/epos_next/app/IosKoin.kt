package epos_next.app

import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {}

fun initKoinIos(): KoinApplication = initKoin(
    module {}
)