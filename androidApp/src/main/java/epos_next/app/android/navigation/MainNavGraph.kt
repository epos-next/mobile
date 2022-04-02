package epos_next.app.android.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import epos_next.app.android.components.MainBottomSheetScreen
import epos_next.app.android.feats.home.HomeScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavGraph(navController: NavHostController, openSheet: (MainBottomSheetScreen) -> Unit) {
    val homeScreenScrollState = rememberScrollState()
    val marksScreenScrollState = rememberScrollState()
    val userProfileScreenScrollState = rememberScrollState()

    DisposableEffect(Unit) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            Firebase.analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
                param(FirebaseAnalytics.Param.SCREEN_NAME, destination.route!!)
                param(FirebaseAnalytics.Param.SCREEN_CLASS, destination.route!!)
            }
        }

        onDispose {}
    }

    AnimatedNavHost(navController = navController, startDestination = Routes.Main.home) {

        // home
        composable(
            route = Routes.Main.home,
        ) { HomeScreen(homeScreenScrollState, openSheet) }

        // Marks
        marksNavGraph(navController, scrollState = marksScreenScrollState)

        // Profile
        profileNavGraph(navController, userScrollState = userProfileScreenScrollState)
    }
}