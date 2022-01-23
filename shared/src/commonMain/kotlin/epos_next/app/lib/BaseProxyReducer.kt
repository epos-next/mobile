package epos_next.app.lib

import io.ktor.utils.io.core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseProxyReducer<T>(val initialState: T) {
    abstract val state: Flow<T>

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