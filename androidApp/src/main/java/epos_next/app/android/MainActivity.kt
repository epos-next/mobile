package epos_next.app.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import co.touchlab.kermit.Logger
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import epos_next.app.android.components.theme.ApplicationTheme
import epos_next.app.android.feats.version.screens.MajorUpdateScreen
import epos_next.app.android.navigation.RootNavGraph
import epos_next.app.android.navigation.Routes
import epos_next.app.network.Api
import epos_next.app.state.dark_mode.DarkModeReducer
import epos_next.app.state.user.UserReducer
import epos_next.app.state.user.UserState
import epos_next.app.usecases.FetchBigDataObjectUseCase
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.android.inject
import kotlin.time.ExperimentalTime

private lateinit var firebaseAnalytics: FirebaseAnalytics

@ExperimentalTime
@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val userReducer: UserReducer by inject()
    private val darkModeReducer: DarkModeReducer by inject()
    private val fetchBigDataObjectUseCase: FetchBigDataObjectUseCase by inject()
    private val api: Api by inject()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        // remove top bar
        supportActionBar?.hide()

        // Firebase
        firebaseAnalytics = Firebase.analytics
        Firebase.crashlytics.setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)
        Firebase.analytics.setAnalyticsCollectionEnabled(!BuildConfig.DEBUG)

        Firebase.analytics.logEvent(FirebaseAnalytics.Event.APP_OPEN) {
            param(FirebaseAnalytics.Param.ITEM_NAME, "Open app")
        }


        setContent {
            val navController = rememberAnimatedNavController()
            val systemUiController = rememberSystemUiController()

            LaunchedEffect("on-start") {
                checkForUpdates(navController)
            }

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
                                        Firebase.crashlytics.setUserId("${it.user.username}-${it.user.id}")
                                        Firebase.analytics.setUserId("${it.user.username}-${it.user.id}")
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
                                        Firebase.crashlytics.setUserId("")
                                        Firebase.analytics.setUserId("")
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

    private suspend fun checkForUpdates(navController: NavController) {
        println("\n\n\n\n\n running checkForUpdates")
        val appUpdateManager = AppUpdateManagerFactory.create(this)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            println("\n\n\n")
            println(appUpdateInfo.toString())
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
            ) {
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    AppUpdateType.IMMEDIATE,
                    this,
                    UPDATE_REQUEST_CODE
                )
            }
        }

        val versionId = BuildConfig.VERSION_CODE
        api.getVersionUrgency(versionId).fold(
            { Logger.withTag("checkForUpdates").e("failed to check app update", it) },
            {
                navController.navigate(Routes.majorUpdate)
            }
        )
    }
}

private const val UPDATE_REQUEST_CODE = 176
