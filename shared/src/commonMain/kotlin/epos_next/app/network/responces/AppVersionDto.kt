package epos_next.app.network.responces

import epos_next.app.domain.entities.AppVersion
import kotlinx.serialization.Serializable

@Serializable
data class AppVersionDtoResource(
    val type: String,
    val success: Boolean,
    val version: String? = null,
)

fun parseAppVersionDto(resource: AppVersionDtoResource): AppVersion {
    return when (resource.type) {
        "up-to-date" -> AppVersion.UpToDate
        "new-update" -> AppVersion.NewUpdate(resource.version!!)
        "new-major-update" -> AppVersion.NewMajorUpdate(resource.version!!)
        else -> throw IllegalArgumentException(resource.toString())
    }
}
