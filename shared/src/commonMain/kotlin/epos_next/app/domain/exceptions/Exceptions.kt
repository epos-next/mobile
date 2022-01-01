package epos_next.app.domain.exceptions

class NetworkException(cause: Throwable? = null): Exception("Can't access internet. Maybe client is offline", cause)