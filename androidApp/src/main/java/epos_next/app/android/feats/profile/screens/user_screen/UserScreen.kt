package epos_next.app.android.feats.profile.screens.user_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import epos_next.app.android.R
import epos_next.app.android.feats.profile.screens.components.ProfileHeader

@Composable
fun UserScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        ProfileHeader(
            image = R.drawable.user_icon_96,
            color = Color(0xFF68D676),
            text = "Профиль",
            navController = navController,
        )
    }
}