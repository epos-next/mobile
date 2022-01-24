package epos_next.app.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import epos_next.app.android.components.theme.ApplicationTheme
import epos_next.app.android.navigation.RootNavGraph
import epos_next.app.android.navigation.Routes
import epos_next.app.state.authStatus.AuthStatusReducer
import epos_next.app.state.authStatus.AuthStatusState
import epos_next.app.usecases.FetchBigDataObjectUseCase
import io.github.aakira.napier.Napier
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.android.inject
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.flow.collect


@ExperimentalTime
@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val authStatusReducer: AuthStatusReducer by inject()
    private val fetchBigDataObjectUseCase: FetchBigDataObjectUseCase by inject()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        // remove top bar
        supportActionBar?.hide()

        setContent {
            val navController = rememberAnimatedNavController()

            ApplicationTheme {
                RootNavGraph(navController)
            }

            lifecycleScope.launchWhenStarted {
                authStatusReducer.state.collect {
                    when (it) {
                        is AuthStatusState.Authorized -> {
                            navController.navigate(Routes.Main.route) {
                                navController.graph.startDestinationRoute?.let { screen_route ->
                                    popUpTo(screen_route)
                                }
                            }

                            fetchBigDataObjectUseCase()
                        }
                        is AuthStatusState.NotAuthorized -> {
                            navController.navigate(Routes.login) {
                                navController.graph.startDestinationRoute?.let { screen_route ->
                                    popUpTo(screen_route)
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
