package epos_next.app.android.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import epos_next.app.android.R
import epos_next.app.android.navigation.Routes

@Composable
fun BottomNavBar(navController: NavHostController) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
    ) {
        val route = currentRoute(navController)

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Image(
                        painter = painterResource(id = item.getIcon(route)),
                        contentDescription = item.description,
                        modifier = Modifier.size(32.dp)
                    )
                },
                selected = item.selected(route),
                onClick = {
                    if (!item.selected(route)) {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

private data class BottomBarItem(
    @DrawableRes val activeIcon: Int,
    @DrawableRes val disabledIcon: Int,
    val route: String,
    val description: String,
) {
    fun selected(currentRoute: String?) = currentRoute != null && currentRoute.contains(route)

    @DrawableRes
    fun getIcon(currentRoute: String?): Int {
        return if (selected(currentRoute)) activeIcon
        else disabledIcon
    }
}

private val items = listOf(
    BottomBarItem(
        activeIcon = R.drawable.home_active_icon,
        disabledIcon = R.drawable.home_disabled_icon,
        route = Routes.Main.home,
        description = "home"
    ),
    BottomBarItem(
        activeIcon = R.drawable.marks_active_icon,
        disabledIcon = R.drawable.marks_disabled_icon,
        route = Routes.Main.Marks.route,
        description = "marks"
    ),
    BottomBarItem(
        activeIcon = R.drawable.user_active_icon,
        disabledIcon = R.drawable.user_disabled_icon,
        route = Routes.Main.profile,
        description = "profile"
    ),
)