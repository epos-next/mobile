package epos_next.app.network

object ApiRoutes {
    const val baseRoute = "https://1df840c4-2bfd-4ec4-b2bd-5ec06716e007.mock.pstmn.io/api/1.0/"

    const val authenticate = "/auth/authenticate"
    const val reauthenticate = "/auth/reauthenticate"

    const val data = "/data"
    fun fetchLesson(from: String, to: String) = "/data/lessons?from=$from&to=$to"
    fun completeHomework(id: Long) = "/homework/$id/complete"
    fun cancelCompleteHomework(id: Long) = "/homework/$id/cancel-complete"
}