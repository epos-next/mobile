package epos_next.app.android.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.slideEnter(): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(400)
    )
}

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.slideExit(): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(400)
    )
}