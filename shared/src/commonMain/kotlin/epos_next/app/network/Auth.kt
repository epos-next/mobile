package epos_next.app.network

import co.touchlab.kermit.Logger
import epos_next.app.data.auth.AuthDataStoreImpl
import epos_next.app.models.SetAuthTokens
import epos_next.app.network.requests.auth.ReauthenticateRequest
import epos_next.app.network.responces.auth.ReauthenticateResponse
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

private val httpLogger = Logger.withTag("HTTP")

suspend fun handleUnauthorizedStatus(e: ResponseException): String? {
    httpLogger.d("status = ${e.response.status}")
    if (e.response.status != HttpStatusCode.Unauthorized) return null

    val authDataStore = AuthDataStoreImpl()
    val token = authDataStore.getRefreshToken()
    val id = authDataStore.getId()

    httpLogger.i("need to refresh tokens. Id is $id, refresh token is $token")

    try {
        if (token != null && id != null) {
            val updateResponse: ReauthenticateResponse =
                tokenClient.post(ApiRoutes.reauthenticate) {
                    body = ReauthenticateRequest(
                        refresh = token,
                        id = id,
                    )
                }

            httpLogger.i("update token response = `$updateResponse")

            if (updateResponse.success) {
                val setPayload = SetAuthTokens.fromAuthTokens(updateResponse.tokens)
                authDataStore.setTokens(setPayload)
                authDataStore.setId(updateResponse.id)
                return setPayload.access
            }
        }
        return null
    } catch (e: Exception) {
        httpLogger.e("failed to refresh auth tokens", e)
        return null
    }
}