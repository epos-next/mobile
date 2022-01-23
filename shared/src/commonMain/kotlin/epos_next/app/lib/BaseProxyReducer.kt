package epos_next.app.lib

import io.ktor.utils.io.core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent

abstract class BaseProxyReducer<T>(val initialState: T): KoinComponent {
    abstract val state: Flow<T>

    val scope = CoroutineScope(Dispatchers.Main)

    fun onChange(provideNewState: ((T) -> Unit)): Closeable {
        val job = Job()

        state
            .onEach { provideNewState.invoke(it) }
            .launchIn(CoroutineScope(Dispatchers.Main + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}