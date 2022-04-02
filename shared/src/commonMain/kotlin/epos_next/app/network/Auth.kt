package epos_next.app.network

import co.touchlab.kermit.Logger
import epos_next.app.data.auth.AuthDataStoreImpl
import epos_next.app.models.SetAuthTokens
import epos_next.app.network.requests.auth.ReauthenticateRequest
import epos_next.app.network.responces.auth.ReauthenticateResponse
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

suspend fun handleUnauthorizedStatus(e: ResponseException): String? {
    Logger.d("status = ${e.response.status}")
    if (e.response.status != HttpStatusCode.Unauthorized) return null

    val authDataStore = AuthDataStoreImpl()
    val token = authDataStore.getRefreshToken()
    val id = authDataStore.getId()

    Logger.i("need to refresh tokens. Id is $id, refresh token is $token")

    try {
        if (token != null && id != null) {
            val updateResponse: ReauthenticateResponse =
                tokenClient.post(ApiRoutes.reauthenticate) {
                    body = ReauthenticateRequest(
                        refresh = token,
                        id = id,
                    )
                }

            Logger.i("update token response = `$updateResponse")

            if (updateResponse.success) {
                val setPayload = SetAuthTokens.fromAuthTokens(updateResponse.tokens)
                authDataStore.setTokens(setPayload)
                authDataStore.setId(updateResponse.id)
                return setPayload.access
            }
        }
        return null
    } catch (e: Exception) {
        Logger.e("failed to refresh auth tokens", e)
        return null
    }
}