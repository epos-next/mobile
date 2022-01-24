package epos_next.app.android

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import epos_next.app.android.components.BottomNavBar
import epos_next.app.android.navigation.MainNavGraph

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainView() {
    val navController = rememberAnimatedNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) {
        MainNavGraph(navController)
    }
}