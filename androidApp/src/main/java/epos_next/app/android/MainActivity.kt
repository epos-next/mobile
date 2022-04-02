package epos_next.app.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import epos_next.app.android.components.theme.ApplicationTheme
import epos_next.app.android.navigation.RootNavGraph
import epos_next.app.android.navigation.Routes
import epos_next.app.state.dark_mode.DarkModeReducer
import epos_next.app.state.user.UserReducer
import epos_next.app.state.user.UserState
import epos_next.app.usecases.FetchBigDataObjectUseCase
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.android.inject
import kotlin.time.ExperimentalTime

@ExperimentalTime
@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val userReducer: UserReducer by inject()
    private val darkModeReducer: DarkModeReducer by inject()
    private val fetchBigDataObjectUseCase: FetchBigDataObjectUseCase by inject()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        // remove top bar
        supportActionBar?.hide()

        setContent {
            val navController = rememberAnimatedNavController()
            val systemUiController = rememberSystemUiController()

            val isDarkMode by darkModeReducer.state.collectAsState()

            if (isDarkMode) {
                systemUiController.setSystemBarsColor(Color(0xFF1D1A25))
                systemUiController.setStatusBarColor(Color(0xFF11111B))
            } else {
                systemUiController.setSystemBarsColor(Color.White)
                systemUiController.setStatusBarColor(Color.Black)
            }

            ApplicationTheme(darkTheme = isDarkMode) {
                RootNavGraph(navController)
            }

            lifecycleScope.launchWhenStarted {
                userReducer.state.collect {
                    when (it) {
                        is UserState.Authorized -> {
                            if (navController.currentBackStackEntry?.destination?.route != Routes.Main.route) {
                                navController.navigate(Routes.Main.route) {
                                    navController.graph.startDestinationRoute?.let { screen_route ->
                                        popUpTo(screen_route)
                                    }
                                }
                                fetchBigDataObjectUseCase()
                            }
                        }
                        is UserState.NotAuthorized -> {
                            if (navController.currentBackStackEntry?.destination?.route != Routes.login) {
                                navController.navigate(Routes.login) {
                                    navController.graph.startDestinationRoute?.let { screen_route ->
                                        popUpTo(screen_route)
                                    }
                                }
                            }
                        }
                        else -> Unit
                    }
                }

            }
        }
    }
}
