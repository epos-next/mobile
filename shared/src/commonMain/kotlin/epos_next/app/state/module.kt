package epos_next.app.state

import epos_next.app.state.authStatus.AuthStatusReducer
import org.koin.dsl.module

val stateModule = module {
    single { AuthStatusReducer() }
}