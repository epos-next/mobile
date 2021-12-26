package epos_next.app.domain.exceptions

open class AuthException(message: String): Exception("AuthException: $message")

class InvalidCredentials: AuthException("Invalid email or password")
class InvalidAuthException: AuthException("Invalid exception happened")
class InvalidAuthentication: AuthException("Can't use access and refresh token. Both invalid")
class RefreshFailed: AuthException("Api reject response. It's not possible to update the token")

open class TokenException(message: String) : Exception(message)
class NoTokenFoundException : TokenException("Can't update token because can't found old token")
class InvalidTokenFoundException : TokenException("Can't update token because received token is invalid")
class TokenExpiredException(type: String) : TokenException("Need to update $type token")