package epos_next.app.network

import co.touchlab.kermit.Logger
import io.ktor.client.features.logging.Logger as KtorLogger

class CustomHttpLogger: KtorLogger {
    override fun log(message: String) {
        Logger.i(message)
    }
}