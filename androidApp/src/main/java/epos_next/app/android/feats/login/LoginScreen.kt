package epos_next.app.android.feats.login

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import epos_next.app.android.components.theme.ApplicationTheme
import epos_next.app.android.feats.login.parts.Form
import epos_next.app.android.feats.login.parts.LogoAndName
import epos_next.app.android.feats.login.parts.TopBar
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class, ExperimentalAnimationApi::class)
@Composable
fun LoginScreen() {
    ApplicationTheme {
        val focusManager = LocalFocusManager.current

        Scaffold(
            topBar = { TopBar() },
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) { focusManager.clearFocus() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                LogoAndName()
                Form()
            }
        }
    }
}