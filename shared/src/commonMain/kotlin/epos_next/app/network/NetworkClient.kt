package epos_next.app.network

import epos_next.app.data.auth.AuthDataStore
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.utils.*
import io.ktor.http.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.native.concurrent.SharedImmutable
import kotlin.time.ExperimentalTime

@SharedImmutable
val tokenClient = HttpClient {
    defaultRequest {
        url.takeFrom(URLBuilder().takeFrom(ApiRoutes.baseRoute).apply {
            encodedPath += url.encodedPath
        })
        contentType(ContentType.Application.Json)
    }
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
    install(HttpLogging)
}

@SharedImmutable
val authClient = HttpClient {
    defaultRequest {
        url.takeFrom(URLBuilder().takeFrom(ApiRoutes.baseRoute).apply {
            encodedPath += url.encodedPath
        })
        contentType(ContentType.Application.Json)
    }
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
    install(HttpLogging) {}
}

@SharedImmutable
@OptIn(ExperimentalTime::class)
val httpClient: HttpClient = HttpClient {
    defaultRequest {
        url.takeFrom(URLBuilder().takeFrom(ApiRoutes.baseRoute).apply {
            encodedPath += url.encodedPath
        })
        contentType(ContentType.Application.Json)
    }
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
    install(HttpLogging)
}

class NetworkClient : KoinComponent {
    val authDataStore: AuthDataStore by inject()

    @OptIn(ExperimentalTime::class)
    inline fun getAuthorizationHeader(): String {
        return authDataStore.getTokens().fold(
            { "Bearer NULL" },
            { "Bearer ${it.access}" }
        )
    }

    suspend inline fun <reified T> get(route: String): T {
        return httpClient.get(route) {
            headers {
                append("Authorization", this@NetworkClient.getAuthorizationHeader())
            }
        }
    }

    suspend inline fun <reified T> post(route: String, body: Any = EmptyContent): T {
        return httpClient.post(route) {
            this.body = body
            headers {
                append("Authorization", this@NetworkClient.getAuthorizationHeader())
            }
        }
    }

    suspend inline fun <reified T> put(route: String, body: Any = EmptyContent): T {
        return httpClient.put(route) {
            this.body = body
            headers {
                append("Authorization", this@NetworkClient.getAuthorizationHeader())
            }
        }
    }
}