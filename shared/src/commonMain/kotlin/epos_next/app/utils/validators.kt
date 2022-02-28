package epos_next.app.utils

fun validateUserName(name: String): String? {
    if (name.isBlank()) return "Это поле обязательно"
    if (name.length > 100) return "Имя не может быть длиннее 100 символов"
    return null
}

fun validateUserUsername(username: String): String? {
    if (username.isBlank()) return "Это поле обязательно"
    if (username.length > 100) return "Username не может быть длиннее 100 символов"
    if (!"^[a-zA-Z0-9._\\-]+\$".toRegex().matches(username))
        return "Username может содержать только буквы латинского алфавита, символы _, - и ."
    return null
}