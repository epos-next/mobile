package epos_next.app.network

import epos_next.app.data.auth.AuthDataStore
import epos_next.app.models.SetAuthTokens
import epos_next.app.network.responces.auth.ReauthenticateResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.time.ExperimentalTime

internal interface NetworkClient {
    @ExperimentalTime
    val client: HttpClient
}

internal class NetworkClientImpl : NetworkClient, KoinComponent {

    private val authDataStore by inject<AuthDataStore>()

    private val tokenClient = HttpClient {
        defaultRequest {
            url(ApiRoutes.baseRoute)
            contentType(ContentType.Application.Json)
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    @ExperimentalTime
    override val client: HttpClient = HttpClient {
        defaultRequest {
            url(ApiRoutes.baseRoute)
            contentType(ContentType.Application.Json)
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        install(Auth) {
            bearer {
                loadTokens {
                    authDataStore.getTokens().fold(
                        {
                            print(it)
                            null
                        },
                        {
                            BearerTokens(
                                accessToken = it.access!!,
                                refreshToken = it.refresh
                            )
                        }
                    )
                }
                refreshTokens {
                    val token = authDataStore.getRefreshToken()
                    val id = authDataStore.getId()

                    if (token != null) {
                        val updateResponse: ReauthenticateResponse =
                            tokenClient.post(ApiRoutes.reauthenticate) {
                                body = object {
                                    val refresh = token
                                    val id = id
                                }
                            }

                        if (updateResponse.success) {
                            val setPayload = SetAuthTokens.fromAuthTokens(updateResponse.tokens!!)
                            authDataStore.setTokens(setPayload)
                            authDataStore.setId(updateResponse.id!!)

                            BearerTokens(
                                accessToken = setPayload.access,
                                refreshToken = setPayload.refresh,
                            )
                        }
                    }

                    null
                }
            }
        }
    }
}