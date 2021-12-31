package epos_next.app.data.user

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import epos_next.app.data.settings
import epos_next.app.domain.entities.User
import io.github.aakira.napier.Napier
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.core.component.KoinComponent

class UserDataSourceImpl: UserDataSource, KoinComponent {

    @OptIn(ExperimentalSettingsApi::class, ExperimentalSerializationApi::class)
    override fun save(user: User) {
        settings.encodeValue(User.serializer(), "user", user)
        Napier.i("save user = $user", tag = "DB")
    }

    @OptIn(ExperimentalSettingsApi::class, ExperimentalSerializationApi::class)
    override fun get(): User? {
        return settings.decodeValueOrNull(User.serializer(), "user").let {
            Napier.i("get user = $it", tag = "DB")
            it
        }
    }
}