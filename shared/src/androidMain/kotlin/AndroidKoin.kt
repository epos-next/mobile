package epos_next.app

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import epos_next.db.AppDatabase
import org.koin.dsl.module
import org.koin.core.module.Module


actual val platformModule: Module = module {
    single<SqlDriver> { AndroidSqliteDriver(AppDatabase.Schema, get(), "EposNext") }
}
