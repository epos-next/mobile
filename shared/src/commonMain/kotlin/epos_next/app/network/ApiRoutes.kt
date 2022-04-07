package epos_next.app.network

object ApiRoutes {
    const val baseRoute = "https://epos-api.zotov.dev"

    const val authenticate = "/api/1.1/auth/authenticate"
    const val reauthenticate = "/api/1.0/auth/reauthenticate"

    const val data = "/api/1.1/data"
    fun fetchLesson(from: String, to: String) = "/api/1.1/data/lessons?from=$from&to=$to"
    fun completeHomework(id: Long) = "/api/1.0/homework/$id/complete"
    fun cancelCompleteHomework(id: Long) = "/api/1.0/homework/$id/cancel-complete"

    const val createControlWork = "/api/1.0/control-work"
    const val createAdvertisement = "/api/1.0/advertisement"

    const val updateUser = "/api/1.0/user"
}