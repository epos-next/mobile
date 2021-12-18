package epos_next.app.models

data class AuthTokens(
    val access: String?,
    val refresh: String,
)

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


