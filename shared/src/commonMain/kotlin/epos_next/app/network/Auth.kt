package epos_next.app.network

import epos_next.app.data.auth.AuthDataStoreImpl
import epos_next.app.models.SetAuthTokens
import epos_next.app.network.requests.auth.ReauthenticateRequest
import epos_next.app.network.responces.auth.ReauthenticateResponse
import io.github.aakira.napier.Napier
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

suspend fun handleUnauthorizedStatus(e: ResponseException): Boolean {
    Napier.d("status = ${e.response.status}")
    if (e.response.status != HttpStatusCode.Unauthorized) return false

    val authDataStore = AuthDataStoreImpl()
    val token = authDataStore.getRefreshToken()
    val id = authDataStore.getId()

    Napier.i("need to refresh tokens. Id is $id, refresh token is $token")

    return try {
        if (token != null && id != null) {
            val updateResponse: ReauthenticateResponse =
                tokenClient.post(ApiRoutes.reauthenticate) {
                    body = ReauthenticateRequest(
                        refresh = token,
                        id = id,
                    )
                }

            Napier.i("update token response = `$updateResponse")

            if (updateResponse.success) {
                val setPayload = SetAuthTokens.fromAuthTokens(updateResponse.tokens)
                authDataStore.setTokens(setPayload)
                authDataStore.setId(updateResponse.id)
                return true
            }
        }
        return false
    } catch (e: Exception) {
        Napier.e("failed to refresh auth tokens", e, tag = "HTTP")
        Napier.e(e.toString(), tag = "HTTP")
        false
    }
}