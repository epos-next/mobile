package epos_next.app.android.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import epos_next.app.android.feats.marks.detail.MarksDetailScreen
import epos_next.app.android.feats.marks.main.MarksScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.marksNavGraph(navController: NavHostController, scrollState: ScrollState) {
    navigation(
        startDestination = Routes.Main.Marks.main,
        route = Routes.Main.Marks.route,
    ) {
        // Main
        composable(
            route = Routes.Main.Marks.main,
        ) { MarksScreen(
            navController = navController,
            scrollState = scrollState
        ) }

        // Detail
        composable(
            route = Routes.Main.Marks.detail("{subject}"),
            enterTransition = {
                if (this.initialState.destination.route == Routes.Main.Marks.main) slideEnter()
                else fadeIn()
            },
            exitTransition = {
                if (this.targetState.destination.route == Routes.Main.Marks.main) slideExit()
                else fadeOut()
             },
        ) { backStackEntry ->
            val subject = backStackEntry.arguments?.getString("subject")
            if (subject != null) {
                MarksDetailScreen(navController = navController, subject = subject)
            }
        }
    }
}