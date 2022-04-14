package epos_next.app.network

import co.touchlab.kermit.Logger
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.util.*
import io.ktor.utils.io.*
import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

private val format = Json { prettyPrint = true }

/**
 * [HttpClient] logging feature.
 */
class HttpLogging {
    private val mutex = Mutex()
    private val logger = Logger.withTag("HTTP")

    private suspend fun beginLogging() {
        mutex.lock()

    }

    private fun doneLogging() {
        mutex.unlock()
    }

    class Config

    @OptIn(DelicateCoroutinesApi::class)
    private suspend fun logRequest(request: HttpRequestBuilder): OutgoingContent? {
        val body = request.body as OutgoingContent
        val charset = body.contentType?.charset() ?: Charsets.UTF_8

        var message = "REQUEST:\n" +
                "${request.method.value} ${Url(request.url)}\n" +
                "Authorization: ${request.headers["Authorization"]}\n"

        val channel = ByteChannel()
        GlobalScope.launch(Dispatchers.Unconfined) {
            val text = channel.tryReadText(charset) ?: ""
            if (text != "") {
                val json = Json.decodeFromString<JsonObject>(message)
                val prettyMessage = format.encodeToString(json)
                message += "BODY:\n$prettyMessage"
            }
        }

        logger.i { message }

        return null
    }

    private fun logResponse(response: HttpResponse) {
        logger.i {
            "RESPONSE:\n" +
                    "${response.call.request.method.value} ${response.call.request.url}\n"
        }
    }

    private suspend fun logResponseBody(contentType: ContentType?, content: ByteReadChannel): Unit =
        with(logger) {
            val message = content.tryReadText(contentType?.charset() ?: Charsets.UTF_8)
                ?: ""

            if (message != "") {
                val json = Json.decodeFromString<JsonObject>(message)
                val prettyMessage = format.encodeToString(json)
                logger.i {
                    "BODY:\n$prettyMessage"
                }
            }

        }

    private fun logRequestException(context: HttpRequestBuilder, cause: Throwable) {
        logger.w("REQUEST ${Url(context.url)} failed with exception: $cause")
    }

    private fun logResponseException(context: HttpClientCall, cause: Throwable) {
        if (context.response.status == HttpStatusCode.Forbidden) return
        logger.w("RESPONSE ${context.request.url} failed with exception: $cause")
    }

    companion object : HttpClientFeature<Config, HttpLogging> {
        override val key: AttributeKey<HttpLogging> = AttributeKey("HttpLogging")

        override fun prepare(block: Config.() -> Unit): HttpLogging {
            return HttpLogging()
        }

        override fun install(feature: HttpLogging, scope: HttpClient) {
            scope.sendPipeline.intercept(HttpSendPipeline.Monitoring) {
                val response = try {
                    feature.beginLogging()
                    feature.logRequest(context)
                } catch (_: Throwable) {
                    null
                } finally {
                    feature.doneLogging()
                }

                try {
                    proceedWith(response ?: subject)
                } catch (cause: Throwable) {
                    feature.logRequestException(context, cause)
                    throw cause
                }
            }

            scope.receivePipeline.intercept(HttpReceivePipeline.State) {
                try {
                    feature.beginLogging()
                    feature.logResponse(context.response)
                    proceedWith(subject)
                } catch (cause: Throwable) {
                    feature.logResponseException(context, cause)
                    throw cause
                } finally {
                    feature.doneLogging()
                }
            }

            scope.responsePipeline.intercept(HttpResponsePipeline.Receive) {
                try {
                    proceed()
                } catch (cause: Throwable) {
                    feature.logResponseException(context, cause)
                    throw cause
                }
            }

            val observer: ResponseHandler = {
                try {
                    feature.logResponseBody(it.contentType(), it.content)
                } catch (_: Throwable) {
                } finally {
                    feature.doneLogging()
                }
            }

            ResponseObserver.install(ResponseObserver(observer), scope)
        }
    }
}


internal suspend inline fun ByteReadChannel.tryReadText(charset: Charset): String? = try {
    readRemaining().readText(charset = charset)
} catch (cause: Throwable) {
    null
}
