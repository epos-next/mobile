package epos_next.app.network

import org.koin.dsl.module

val networkModule = module {
    single<Api> { ApiImpl() }
    single<NetworkClient> { NetworkClient() }
}