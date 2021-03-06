package epos_next.app.android.feats.profile.screens.profile_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import epos_next.app.android.R
import epos_next.app.android.components.ErrorText
import epos_next.app.android.components.SwitchComponent
import epos_next.app.android.feats.loading.LoadingScreen
import epos_next.app.android.feats.profile.screens.profile_screen.components.Tile
import epos_next.app.android.feats.profile.screens.profile_screen.components.UserAvatar
import epos_next.app.android.feats.profile.screens.profile_screen.components.UserName
import epos_next.app.android.navigation.Routes
import epos_next.app.state.dark_mode.DarkModeReducer
import epos_next.app.state.user.UserReducer
import epos_next.app.state.user.UserState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun ProfileScreen(navController: NavHostController) {
    val reducer = get<UserReducer>()
    val state = reducer.state.collectAsState().value
    val coroutineScope = rememberCoroutineScope()

    fun handleClick() {
        coroutineScope.launch {
            Firebase.analytics.logEvent("logout") {
                param(FirebaseAnalytics.Param.ITEM_NAME, "Logout")
            }
            reducer.logout()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        when (state) {
            is UserState.Authorized -> {
                val user = state.user

                UserAvatar(modifier = Modifier.padding(bottom = 20.dp, top = 50.dp))
                UserName(user.name, modifier = Modifier.padding(bottom = 27.5.dp))

                Tile(
                    "??????????????",
                    icon = R.drawable.user_icon,
                    color = Color(0xFF68D676),
                    onTap = { navController.navigate(Routes.Main.Profile.user) }
                )

                val darkModeReducer = get<DarkModeReducer>()
                val isDark by darkModeReducer.state.collectAsState()

                Tile(
                    "????????",
                    icon = R.drawable.moon_icon,
                    color = Color(0xFF4957CD),
                    onTap = { darkModeReducer.change(!isDark) }
                ) {
                    SwitchComponent(isDark) {
                        Firebase.analytics.logEvent("change_theme") {
                            param(FirebaseAnalytics.Param.ITEM_NAME, "Change theme")
                            param(FirebaseAnalytics.Param.VALUE, if (!isDark) "Dark" else "Light")
                        }
                        darkModeReducer.change(!isDark)
                    }
                }

                Tile(
                    "?? ??????????????????????????",
                    icon = R.drawable.dev_icon,
                    color = Color(0xFF83D4FC),
                    onTap = { navController.navigate(Routes.Main.Profile.aboutDevs)  }
                )

                Tile(
                    "??????????",
                    icon = R.drawable.exit_icon,
                    color = Color(0xFFF18477),
                    ::handleClick,
                )
            }
            is UserState.Loading -> LoadingScreen()
            is UserState.NotAuthorized -> ErrorText("You are not authorized!")
        }

    }
}