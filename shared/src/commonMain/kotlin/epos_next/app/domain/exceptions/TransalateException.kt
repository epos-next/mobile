package epos_next.app.domain.exceptions

fun translateException(exception: Throwable): String {
    return when(exception) {
        is InvalidCredentials -> "Неверный email или пароль"
        is NetworkException -> "Похоже что вы не подключены к интернету"
        else -> "Ой... Произошла непредвиденная ошибка."
    }
}