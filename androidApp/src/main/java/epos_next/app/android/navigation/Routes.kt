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

        object Profile {
            const val route = "main/profile"

            const val main = "main/profile/main"

            const val user = "main/profile/user"

            const val aboutDevs = "main/profile/about_devs"
        }

    }
}