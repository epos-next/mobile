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
import epos_next.app.android.feats.login.screens.LoginScreen
import epos_next.app.android.feats.login.screens.SignInWithVkScreen
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
            route = Routes.Login.route,
            enterTransition = {
                if (this.initialState.destination.route == Routes.Login.vk) fadeIn()
                else slideEnter() + fadeIn()
            },
            exitTransition = {
                if (this.targetState.destination.route == Routes.Login.vk) fadeOut()
                else slideExit() + fadeOut()
            },
        ) { LoginScreen(navController) }

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

        composable(
            route = Routes.Login.vk,
            enterTransition = { slideInVertically(initialOffsetY = { height -> height }) + fadeIn() },
            exitTransition = { slideOutVertically(targetOffsetY = { height -> height }) + fadeOut() },
        ) { SignInWithVkScreen(navController) }
    }
}