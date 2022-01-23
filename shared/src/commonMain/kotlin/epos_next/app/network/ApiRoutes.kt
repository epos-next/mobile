package epos_next.app.network

object ApiRoutes {
    const val baseRoute = "https://epos-api.zotov.dev/api/1.0/"

    const val authenticate = "/auth/authenticate"
    const val reauthenticate = "/auth/reauthenticate"

    const val data = "/data"
    fun fetchLesson(from: String, to: String) = "/data/lessons?from=$from&to=$to"
}