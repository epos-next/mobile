package epos_next.app.network

import org.koin.dsl.module

val networkModule = module {
    single<NetworkClient> { NetworkClientImpl() }
}