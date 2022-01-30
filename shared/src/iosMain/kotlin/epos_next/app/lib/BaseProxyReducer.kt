package epos_next.app.lib

import co.touchlab.stately.freeze
import com.squareup.sqldelight.db.Closeable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> BaseProxyReducer<T>.onChange(provideNewState: ((T) -> Unit)): Closeable {
    val job = Job()

    state
        .onEach { it.freeze() }
        .onEach { provideNewState.invoke(it) }
        .launchIn(CoroutineScope(Dispatchers.Main + job))

    return object : Closeable {
        override fun close() {
            job.cancel()
        }
    }
}