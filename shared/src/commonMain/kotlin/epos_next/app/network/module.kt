package epos_next.app.network

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val networkModule = DI.Module("network") {
    bind<NetworkClient>() with singleton { NetworkClientImpl(di) }
}