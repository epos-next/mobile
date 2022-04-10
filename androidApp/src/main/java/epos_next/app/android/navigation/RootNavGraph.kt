package epos_next.app.android.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import epos_next.app.android.MainView
import epos_next.app.android.feats.loading.LoadingScreen
import epos_next.app.android.feats.login.LoginScreen
import epos_next.app.android.feats.version.screens.MajorUpdateScreen

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
            enterTransition = {
                if (Routes.MajorUpdate.match(this.initialState.destination.route)) fadeIn()
                else slideEnter()
            },
            exitTransition = {
                if (Routes.MajorUpdate.match(this.targetState.destination.route)) fadeOut()
                else slideExit()
            },
        ) { MainView() }

        // Major update route
        composable(
            route = Routes.MajorUpdate.route,
            enterTransition = { slideInVertically(initialOffsetY = { height -> height }) },
            exitTransition = { slideOutVertically(targetOffsetY = { height -> height }) },
            arguments = listOf(navArgument("version") { type = NavType.StringType })
        ) { backStackEntry ->
            BackHandler(true) {}
            MajorUpdateScreen(
                navController = navController,
                version = backStackEntry.arguments?.getString("version") ?: "",
            )
        }
    }
}