package epos_next.app.network

import io.github.aakira.napier.Napier
import io.ktor.client.features.logging.Logger

class CustomHttpLogger: Logger {
    override fun log(message: String) {
        Napier.i(message, tag = "HTTP")
    }
}