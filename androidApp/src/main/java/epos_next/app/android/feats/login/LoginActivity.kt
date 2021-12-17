package epos_next.app.android.feats.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import epos_next.app.android.feats.login.parts.Form
import epos_next.app.android.feats.login.parts.LogoAndName
import epos_next.app.android.feats.login.parts.TopBar
import epos_next.app.android.feats.login.parts.UnderFormText
import epos_next.app.android.components.theme.ApplicationTheme

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
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
                        Spacer(modifier = Modifier.height(10.dp))

                        UnderFormText()
                    }
                }
            }
        }

        // remove top bar
        supportActionBar?.hide()
    }
}