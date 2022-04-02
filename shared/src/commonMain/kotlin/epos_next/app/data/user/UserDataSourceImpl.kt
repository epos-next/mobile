package epos_next.app.data.user

import co.touchlab.kermit.Logger
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import epos_next.app.data.settings
import epos_next.app.domain.entities.User
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.core.component.KoinComponent

class UserDataSourceImpl: UserDataSource, KoinComponent {

    private val logger = Logger.withTag("LessonsDataSource")

    @OptIn(ExperimentalSettingsApi::class, ExperimentalSerializationApi::class)
    override fun save(user: User) {
        settings.encodeValue(User.serializer(), "user", user)
        logger.i { "save user = $user" }
    }

    @OptIn(ExperimentalSettingsApi::class, ExperimentalSerializationApi::class)
    override fun get(): User? {
        return settings.decodeValueOrNull(User.serializer(), "user").let {
            logger.i { "get user = $it" }
            it
        }
    }
}