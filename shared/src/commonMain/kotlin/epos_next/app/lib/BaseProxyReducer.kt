package epos_next.app.lib

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent

abstract class BaseProxyReducer<T>(val initialState: T): KoinComponent {
    abstract val state: Flow<T>

    protected val scope = CoroutineScope(Dispatchers.Main)
}