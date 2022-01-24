package epos_next.app.android.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import epos_next.app.android.MainView
import epos_next.app.android.feats.loading.LoadingScreen
import epos_next.app.android.feats.login.LoginScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootNavGraph(navController: NavHostController) {
    AnimatedNavHost(navController = navController, startDestination = Routes.loading) {
        // Loading route
        composable(
            route = Routes.loading,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
        ) { LoadingScreen() }

        // Login route
        composable(
            route = Routes.login,
            enterTransition = { slideEnter() },
            exitTransition = { slideExit() },
        ) { LoginScreen() }

        // Main route
        composable(
            route = Routes.Main.route,
            enterTransition = { slideEnter() },
            exitTransition = { slideExit() },
        ) { MainView() }
    }
}