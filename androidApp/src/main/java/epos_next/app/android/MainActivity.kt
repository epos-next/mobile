package epos_next.app.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import epos_next.app.android.components.theme.ApplicationTheme
import epos_next.app.android.navigation.RootNavGraph
import epos_next.app.android.navigation.Routes
import epos_next.app.state.user.UserReducer
import epos_next.app.state.user.UserState
import epos_next.app.usecases.FetchBigDataObjectUseCase
import io.github.aakira.napier.Napier
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.android.inject
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.get

@ExperimentalTime
@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val userReducer: UserReducer by inject()
    private val fetchBigDataObjectUseCase: FetchBigDataObjectUseCase by inject()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        // remove top bar
        supportActionBar?.hide()

        setContent {
            val navController = rememberAnimatedNavController()

            val darkModeViewModel = get<DarkModeViewModel>()
            val theme by darkModeViewModel.isDarkMode.collectAsState()

            ApplicationTheme(darkTheme = theme) {
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

                    Napier.d("authStatusReducer changed!! Calling FetchBigDataObject!!")
                }

            }
        }
    }
}
