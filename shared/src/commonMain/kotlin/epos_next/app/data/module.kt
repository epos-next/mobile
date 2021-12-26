package epos_next.app.data

import epos_next.app.data.auth.AuthDataStore
import epos_next.app.data.auth.AuthDataStoreImpl
import org.koin.dsl.module

val dataModule = module {
    single<AuthDataStore> { AuthDataStoreImpl() }
}