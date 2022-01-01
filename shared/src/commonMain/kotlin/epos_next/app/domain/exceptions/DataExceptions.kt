package epos_next.app.domain.exceptions

open class DataException(message: String, cause: Throwable? = null): Exception(message, cause)

class InvalidDataException(cause: Throwable? = null) : DataException("Can't fetch data. Invalid exception happened", cause)