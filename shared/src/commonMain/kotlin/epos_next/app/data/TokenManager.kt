package epos_next.app.data

import epos_next.app.models.AuthTokens
import epos_next.app.models.SetAuthTokens

interface AuthDataStore {
    /**
     * Fetch token from data store
     * If no token found -> return null
     * @throws TokenExpiredException if one of token expired
     * @throws InvalidTokenFoundException if set date is not ISO string
     */
    suspend fun getTokens(): AuthTokens?

    /**
     * Fetch token from data store
     * If no token found -> return null
     */
    suspend fun getRefreshToken(): String?

    suspend fun getId(): Int?

    suspend fun setTokens(tokens: SetAuthTokens)

    suspend fun setId(id: Int)

    suspend fun isAuthorized(): Boolean

    /**
     * Check is access token expired
     * @exception NoTokenFoundException if not set date found
     * @exception InvalidTokenFoundException if set date is not ISO string
     */
    suspend fun shouldUpdateAccessToken(): Boolean

    /**
     * Check is refresh token expired
     * @exception NoTokenFoundException if not set date found
     * @exception InvalidTokenFoundException if set date is not ISO string
     */
    suspend fun shouldUpdateRefreshToken(): Boolean

    suspend fun clearAll()
}

