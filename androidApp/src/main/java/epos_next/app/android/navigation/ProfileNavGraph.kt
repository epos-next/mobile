package epos_next.app.android.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import epos_next.app.android.feats.profile.screens.ProfileScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Routes.Main.Profile.main,
        route = Routes.Main.Profile.route,
    ) {
        // main
        composable(
            route = Routes.Main.Profile.main,
        ) { ProfileScreen(navController) }
    }
}