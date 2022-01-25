package epos_next.app.android.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import epos_next.app.android.feats.home.HomeScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavGraph(navController: NavHostController) {
    val homeScreenScrollState = rememberScrollState()
    val marksScreenScrollState = rememberScrollState()

    AnimatedNavHost(navController = navController, startDestination = Routes.Main.home) {

        // home
        composable(
            route = Routes.Main.home,
        ) { HomeScreen(homeScreenScrollState) }

        // Marks
        marksNavGraph(navController, scrollState = marksScreenScrollState)

        // Profile
        profileNavGraph(navController)
    }
}