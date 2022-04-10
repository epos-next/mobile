package epos_next.app.domain.entities

sealed class AppVersion {
    /**
     * No new updates found
     */
    object UpToDate: AppVersion()

    interface NewVersion {
        val version: String
    }

    /**
     * New update with major improvements. Strongly recommendation to update
     */
    data class NewMajorUpdate(override val version: String): AppVersion(), NewVersion

    /**
     * New update with not required improvements
     */
    data class NewUpdate(override val version: String): AppVersion(), NewVersion
}
