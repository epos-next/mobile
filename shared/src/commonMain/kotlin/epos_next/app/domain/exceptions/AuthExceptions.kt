package epos_next.app.domain.exceptions

open class AuthException(message: String): Exception("AuthException: $message")

class InvalidAuthentication: AuthException("Can't use access and refresh token. Both invalid")
class RefreshFailed: AuthException("Api reject response. It's not possible to update the token")