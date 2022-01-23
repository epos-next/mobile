package epos_next.app.network

import epos_next.app.data.auth.AuthDataStoreImpl
import epos_next.app.models.SetAuthTokens
import epos_next.app.network.requests.auth.ReauthenticateRequest
import epos_next.app.network.responces.auth.ReauthenticateResponse
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.cio.*
import kotlin.time.ExperimentalTime

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
    install(Logging) {
        logger = CustomHttpLogger()
        level = LogLevel.ALL
    }
}

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
    install(Logging) {
        logger = CustomHttpLogger()
        level = LogLevel.ALL
    }
}

@OptIn(ExperimentalTime::class)
val client: HttpClient = HttpClient {
    defaultRequest {
        url.takeFrom(URLBuilder().takeFrom(ApiRoutes.baseRoute).apply {
            encodedPath += url.encodedPath
        })
        contentType(ContentType.Application.Json)
    }
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
    install(Logging) {
        logger = CustomHttpLogger()
        level = LogLevel.ALL
    }
    install(Auth) {

        bearer {
            Napier.d("here at bearer")
            loadTokens {
                val authDataStore = AuthDataStoreImpl()
                authDataStore.getTokens().fold(
                    {
                        Napier.e(it.toString())
                        null
                    },
                    {
                        Napier.i("loadTokens: ${it.access} ${it.refresh}")
                        BearerTokens(
                            accessToken = it.access,
                            refreshToken = it.refresh
                        )
                    }
                )
            }
//            refreshTokens {
//                val authDataStore = AuthDataStoreImpl()
//                val token = authDataStore.getRefreshToken()
//                val id = authDataStore.getId()
//
//                Napier.i("need to refresh tokens. Id is $id, refresh token is $token")
//
//                try {
//                    if (token != null && id != null) {
//
//                        val updateResponse: ReauthenticateResponse =
//                            tokenClient.post(ApiRoutes.reauthenticate) {
//                                body = ReauthenticateRequest(
//                                    refresh = token,
//                                    id = id,
//                                )
//                            }
//
//                        if (updateResponse.success) {
//                            val setPayload = SetAuthTokens.fromAuthTokens(updateResponse.tokens)
//                            authDataStore.setTokens(setPayload)
//                            authDataStore.setId(updateResponse.id)
//
//                            BearerTokens(
//                                accessToken = setPayload.access,
//                                refreshToken = setPayload.refresh,
//                            )
//                        }
//                    }
//                } catch (e: Exception) {
//                    Napier.e("failed to refresh auth tokens", e, tag = "HTTP")
//                    Napier.e(e.toString(), tag = "HTTP")
//                }
//
//                null
//            }
        }
    }
}