package epos_next.app.models

import kotlinx.serialization.Serializable

/**
 * Presents cookies required for authorization via VK
 *
 * Stores cookies as header
 */
@Serializable
data class VkCookies(
    /**
     * cookies for login.vk.com
     */
    val login: String,

    /**
     * cookies for oauth.vk.com
     */
    val oauth: String,

    /**
     * cookies for vk.com
     */
    val main: String,
)
