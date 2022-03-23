package epos_next.app.domain.exceptions

import kotlinx.serialization.SerializationException

fun translateException(exception: Throwable): String {
    return when(exception) {
        is InvalidCredentials -> "Неверный email или пароль"
        is NetworkException -> "Похоже что вы не подключены к интернету"
        is SerializationException -> "Сервер ответил какой-то херней\uD83E\uDD74"
        else -> "Ой... Произошла непредвиденная ошибка."
    }
}