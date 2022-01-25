package epos_next.app.android.feats.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import epos_next.app.android.R
import epos_next.app.android.feats.profile.components.Tile
import epos_next.app.android.feats.profile.components.UserAvatar
import epos_next.app.android.feats.profile.components.UserName
import epos_next.app.state.authStatus.AuthStatusReducer
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun ProfileScreen(navController: NavHostController) {
    val reducer = get<AuthStatusReducer>()
    val coroutineScope = rememberCoroutineScope()

    fun handleClick() {
        coroutineScope.launch {
            reducer.logout()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        UserAvatar(modifier = Modifier.padding(bottom = 20.dp, top = 50.dp))
        UserName("User User", modifier = Modifier.padding(bottom = 27.5.dp))
        Tile("Профиль", icon = R.drawable.user_icon, color = Color(0xFF68D676)) {}
        Tile("Уведомление", icon = R.drawable.notification_icon, color = Color(0xFF6D73FD)) {}
        Tile("Тема", icon = R.drawable.theme_icon, color = Color(0xFFF5A664)) {}
        Tile("О разработчиках", icon = R.drawable.dev_icon, color = Color(0xFF83D4FC)) {}
        Tile("Выйти", icon = R.drawable.exit_icon, color = Color(0xFFF18477), ::handleClick)
    }
}