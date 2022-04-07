package epos_next.app.android.feats.profile.screens.about_devs_screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import epos_next.app.android.BuildConfig

@Composable
fun VersionText() {
    Text(
        text = "Версия ${BuildConfig.VERSION_NAME}",
        modifier = Modifier.padding(horizontal = 20.dp),
        style = TextStyle(
            fontSize = 13.sp,
            color = MaterialTheme.colors.secondary,
            lineHeight = 28.sp,
        )
    )
}