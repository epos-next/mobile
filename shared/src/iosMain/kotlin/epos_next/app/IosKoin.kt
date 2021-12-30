package epos_next.app

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import epos_next.db.AppDatabase
import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {}

fun initKoinIos(): KoinApplication = initKoin(
    module {
        single<SqlDriver> { NativeSqliteDriver(AppDatabase.Schema, "EposNext") }
    }
)