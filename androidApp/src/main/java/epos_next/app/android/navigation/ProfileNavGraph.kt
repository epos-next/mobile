package epos_next.app.android.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import epos_next.app.android.feats.profile.screens.about_devs_screen.AboutDevsScreen
import epos_next.app.android.feats.profile.screens.profile_screen.ProfileScreen
import epos_next.app.android.feats.profile.screens.user_screen.UserScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileNavGraph(navController: NavHostController, userScrollState: ScrollState) {
    navigation(
        startDestination = Routes.Main.Profile.main,
        route = Routes.Main.Profile.route,
    ) {
        // main
        composable(
            route = Routes.Main.Profile.main,
        ) { ProfileScreen(navController) }
    }

    // Profile details screen
    composable(
        route = Routes.Main.Profile.user,
        enterTransition = {
            if (this.initialState.destination.route == Routes.Main.Profile.main) slideEnter()
            else fadeIn()
        },
        exitTransition = {
            if (this.targetState.destination.route == Routes.Main.Profile.main) slideExit()
            else fadeOut()
        },
    ) { UserScreen(navController, userScrollState) }

    // Profile devs screen
    composable(
        route = Routes.Main.Profile.aboutDevs,
        enterTransition = {
            if (this.initialState.destination.route == Routes.Main.Profile.main) slideEnter()
            else fadeIn()
        },
        exitTransition = {
            if (this.targetState.destination.route == Routes.Main.Profile.main) slideExit()
            else fadeOut()
        },
    ) { AboutDevsScreen(navController, userScrollState) }

}