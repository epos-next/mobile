package epos_next.app.data

import epos_next.app.data.auth.AuthDataStore
import epos_next.app.data.auth.AuthDataStoreImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val dataModule = DI.Module("Data") {
    bind<AuthDataStore>() with singleton { AuthDataStoreImpl() }
}