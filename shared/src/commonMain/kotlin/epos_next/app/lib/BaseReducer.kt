package epos_next.app.lib

import io.ktor.utils.io.core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent

abstract class BaseReducer<T>(initialState: T): KoinComponent {
    protected val stateFlow = MutableStateFlow(initialState)
    val state: StateFlow<T> get() = stateFlow.asStateFlow()

    fun onChange(provideNewState: ((T) -> Unit)): Closeable {
        val job = Job()

        stateFlow
            .onEach { provideNewState.invoke(it) }
            .launchIn(CoroutineScope(Dispatchers.Main + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}