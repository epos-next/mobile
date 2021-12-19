package epos_next.app.data.auth

import epos_next.app.data.settings
import epos_next.app.models.AuthTokens
import epos_next.app.models.SetAuthTokens
import epos_next.app.domain.exceptions.TokenExpiredException
import epos_next.app.domain.exceptions.InvalidTokenFoundException
import epos_next.app.domain.exceptions.NoTokenFoundException
import epos_next.app.domain.exceptions.TokenException
import epos_next.app.lib.Either
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

internal interface AuthDataStore {
    /**
     * Fetch token from data store
     * If no token found -> return null
     * @return [AuthTokens] with valid auth tokens inside
     * @exception TokenExpiredException if one of token expired
     * @exception NoTokenFoundException if no tokens were found
     * @exception InvalidTokenFoundException if set date is not ISO string
     */
    @ExperimentalTime
    fun getTokens(): Either<TokenException, AuthTokens?>

    /**
     * Fetch token from data store
     * If no token found -> return null
     */
    fun getRefreshToken(): String?

    fun getId(): Int?

    fun setTokens(tokens: SetAuthTokens)

    fun setId(id: Int)

    fun isAuthorized(): Boolean

    /**
     * Check is access token expired
     * @exception NoTokenFoundException if not set date found
     * @exception InvalidTokenFoundException if set date is not ISO string
     */
    @ExperimentalTime
    fun shouldUpdateAccessToken(): Either<TokenException, Boolean>

    /**
     * Check is refresh token expired
     * @exception NoTokenFoundException if not set date found
     * @exception InvalidTokenFoundException if set date is not ISO string
     */
    @ExperimentalTime
    fun shouldUpdateRefreshToken(): Either<TokenException, Boolean>

    fun clearAll()
}

internal class AuthDataStoreImpl: AuthDataStore {

    private object Keys {
        const val accessToken = "auth_token"
        const val authTokenSetDate = "auth_token_set_date"
        const val refreshToken = "refresh_token"
        const val refreshTokenSetDate = "refresh_token_set_date"
        const val id = "id"
    }

    @ExperimentalTime
    override fun getTokens(): Either<TokenException, AuthTokens?> {
        // checking should update tokens
        // do it before checking access because if refresh is expired too function will try to
        // update access using expired refresh

        shouldUpdateRefreshToken().fold(
            { return@getTokens Either.Left(it) },
            { if (it) Either.Left(TokenExpiredException("refresh")) }
        )

        shouldUpdateAccessToken().fold(
            { return@getTokens Either.Left(it) },
            { if (it) Either.Left(TokenExpiredException("access")) }
        )

        // get tokens from store
        val refresh = settings.getStringOrNull(Keys.refreshToken)
        val access = settings.getStringOrNull(Keys.accessToken)

        if (refresh == null || access == null) return Either.Left(NoTokenFoundException())

        print("I: getTokens() - successfully return 2 tokens")

        return Either.Right(AuthTokens(access, refresh))
    }

    override fun getRefreshToken(): String? {
        print("I: getRefreshToken() - running")
        return settings.getStringOrNull(Keys.refreshToken)
    }

    override fun getId(): Int? {
        print("I: getId() - running")
        return settings.getIntOrNull(Keys.id)
    }

    override fun setTokens(tokens: SetAuthTokens) {
        print("I: setTokens() - running $tokens")

        val setDate = Clock.System.now().toString()

        // save tokens
        settings.putString(Keys.accessToken, tokens.access)
        settings.putString(Keys.refreshToken, tokens.refresh)

        // save set date
        settings.putString(Keys.authTokenSetDate, setDate)
        settings.putString(Keys.refreshTokenSetDate, setDate)

        print("setTokens() - successful")
    }

    override fun setId(id: Int) {
        print("I: setId($id) - running")
        settings.putInt(Keys.id, id)
        print("I: setId($id) - successful")
    }

    override fun isAuthorized(): Boolean {
        val value = isAuthorizedInner()
        print("isAuthorized() = $value")
        return value
    }

    private fun isAuthorizedInner(): Boolean {
        settings.getStringOrNull(Keys.refreshToken) ?: return false
        settings.getStringOrNull(Keys.accessToken) ?: return false
        settings.getStringOrNull(Keys.id) ?: return false
        return true
    }

    @ExperimentalTime
    override fun shouldUpdateAccessToken(): Either<TokenException, Boolean> = shouldUpdateToken(
        Keys.authTokenSetDate, ExpiresRules.access
    )

    /**
     * Checking is refresh token expired
     * @exception NoTokenFoundException if not set date found
     * @exception InvalidTokenFoundException if set date is not ISO string
     */
    @ExperimentalTime
    override fun shouldUpdateRefreshToken(): Either<TokenException, Boolean> = shouldUpdateToken(
        Keys.refreshTokenSetDate, ExpiresRules.refresh
    )

    override fun clearAll() {
        print("I: clearAll() - running")
        settings.remove(Keys.accessToken)
        settings.remove(Keys.authTokenSetDate)
        settings.remove(Keys.id)
        settings.remove(Keys.refreshToken)
        settings.remove(Keys.refreshTokenSetDate)
        print("I: clearAll() - successful")
    }

    /**
     * Check is received token expired
     * @exception NoTokenFoundException if not set date found
     * @exception InvalidTokenFoundException if set date is not ISO string
     */
    @ExperimentalTime
    private fun shouldUpdateToken(
        tokenDateKey: String,
        lifetime: Duration
    ): Either<TokenException, Boolean> {
        print("I: shouldUpdateToken($tokenDateKey, $lifetime) - running")

        // get date from store as ISO string
        // if no set date found --> there's no token --> user not authorize --> can't update token
        val stringDate =
            settings.getStringOrNull(tokenDateKey) ?: return Either.Left(NoTokenFoundException())

        // convert string --> Date
        val date = try {
            Instant.parse(stringDate)
        } catch (e: Exception) {
            return Either.Left(InvalidTokenFoundException())
        }

        // calculate when this token will expires
        val expiresAt = date + lifetime

        // check is expires date before or after the now
        // if after --> token isn't expired, else need to update it
        val should = expiresAt >= Clock.System.now()

        print("I: shouldUpdateToken($tokenDateKey, $lifetime) = $should")
        return Either.Right(should)
    }
}
