package epos_next.app.data.advertisement

import epos_next.app.domain.entities.Advertisement
import eposnext.app.data.AdvertisementModel

object AdvertisementMapper {

    fun mapDatabase(db: AdvertisementModel): Advertisement {
        return Advertisement(
            id = db.id,
            content = db.content,
            targetDate = db.targetDate,
        )
    }
}