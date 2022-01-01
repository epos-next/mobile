package epos_next.app.data.marks

import epos_next.app.data.settings
import epos_next.app.domain.entities.Marks

/**
 * Data source which manages the saving of the marks's entity on the device
 * Multiplatform [settings] used under the hood
 */
interface MarksDataSource {

    /**
     * Save marks entity on device using multiplatform settings
     * @param marks entity to save
     * @return nothing
     */
    fun save(marks: Marks)

    /**
     * Get saved marks entity using multiplatform settings
     * @return marks entity if exists
     */
    fun get(): Marks
}