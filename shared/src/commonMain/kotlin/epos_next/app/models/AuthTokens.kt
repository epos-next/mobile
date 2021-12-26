package epos_next.app.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthTokens(
    val access: String?,
    val refresh: String,
)

@Serializable
data class SetAuthTokens(
    val access: String,
    val refresh: String,
) {
    companion object {
        fun fromAuthTokens(obj: AuthTokens): SetAuthTokens {
            return SetAuthTokens(
                refresh = obj.refresh,
                access = obj.access!!,
            )
        }
    }
}


