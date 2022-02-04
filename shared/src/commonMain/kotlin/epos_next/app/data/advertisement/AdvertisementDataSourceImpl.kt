package epos_next.app.data.advertisement

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import epos_next.app.domain.entities.Advertisement
import epos_next.db.AppDatabase
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AdvertisementDataSourceImpl : AdvertisementDataSource, KoinComponent {

    private val database: AppDatabase by inject()

    override fun get(): Flow<List<Advertisement>> {
        return database.advertisementsQueries
            .getFresh()
            .asFlow()
            .mapToList()
            .map { list ->
                list.map { AdvertisementMapper.mapDatabase(it) }
                    .let {
                        Napier.i("getFresh() = $it", tag = "DB")
                        it
                    }
            }
    }

    override fun cacheMany(advertisements: Iterable<Advertisement>) {
        database.advertisementsQueries.transaction {
            // delete old cache
            database.advertisementsQueries.deleteAll()
            Napier.i("deleteAll()", tag = "DB")

            // save new
            advertisements.forEach {
                Napier.i("insert($it)", tag = "DB")
                database.advertisementsQueries.insert(
                    id = it.id,
                    content = it.content,
                    targetDate = it.targetDate,
                )
            }
        }
    }

    override fun cacheOne(advertisement: Advertisement) {
        database.advertisementsQueries.insert(
            id = -1,
            content = advertisement.content,
            targetDate = advertisement.targetDate,
        )
    }

    override fun replaceFakeIdWithReal(content: String, realId: Long) {
        database.advertisementsQueries.replaceWithFakeId(
            content = content,
            realId = realId,
            fakeId = -1,
        )
    }

    override fun clearAll() {
        database.advertisementsQueries.deleteAll()
    }
}