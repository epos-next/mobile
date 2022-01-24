package epos_next.app.android.navigation

object Routes {
    const val login = "login"

    const val loading = "loading"

    object Main {
        const val route = "main"

        const val home = "main/home"

        object Marks {
            const val route = "main/marks"

            const val main = "main/marks/main"

            fun detail(subject: String) = "main/marks/$subject"
        }

        val profile = "main/profile"
    }
}