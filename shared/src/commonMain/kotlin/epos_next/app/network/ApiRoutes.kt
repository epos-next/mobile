package epos_next.app.network

object ApiRoutes {
    const val baseRoute = "https://1df840c4-2bfd-4ec4-b2bd-5ec06716e007.mock.pstmn.io"

    const val authenticate = "/api/1.1/auth/authenticate"
    const val reauthenticate = "/api/1.0/auth/reauthenticate"

    const val data = "/api/1.0/data"
    fun fetchLesson(from: String, to: String) = "/api/1.0/data/lessons?from=$from&to=$to"
    fun completeHomework(id: Long) = "/api/1.0/homework/$id/complete"
    fun cancelCompleteHomework(id: Long) = "/api/1.0/homework/$id/cancel-complete"

    const val createControlWork = "/api/1.0/control-work"
    const val createAdvertisement = "/api/1.0/advertisement"

    const val updateUser = "/api/1.0/user"
}