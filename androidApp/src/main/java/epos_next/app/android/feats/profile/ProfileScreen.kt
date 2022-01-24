package epos_next.app.android.feats.profile

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import epos_next.app.state.authStatus.AuthStatusReducer
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun ProfileScreen() {
    val reducer = get<AuthStatusReducer>()
    val coroutineScope = rememberCoroutineScope()

    fun handleClick() {
        coroutineScope.launch {
            reducer.logout()
        }
    }

    Button(onClick = ::handleClick) {
        Text("logout")
    }
}