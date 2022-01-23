package epos_next.app.android.lib

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import epos_next.app.lib.BaseProxyReducer

@Composable
fun <T> BaseProxyReducer<T>.collectAsState(): T {
    return this.state.collectAsState(initial = this.initialState).value
}