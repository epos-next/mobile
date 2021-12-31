package epos_next.app.data.user

import epos_next.app.domain.entities.User
import epos_next.app.data.settings

/**
 * Data source which manages the saving of the user's entity on the device
 * Multiplatform [settings] used under the hood
 */
interface UserDataSource {

    /**
     * Save user entity on device using multiplatform settings
     * @param user entity to save
     * @return nothing
     */
    fun save(user: User)

    /**
     * Get saved user entity using multiplatform settings
     * @return user entity if exists
     */
    fun get(): User?
}