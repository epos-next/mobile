package epos_next.app.android.feats.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import epos_next.app.android.feats.login.parts.Form
import epos_next.app.android.feats.login.parts.LogoAndName
import epos_next.app.android.feats.login.parts.TopBar
import epos_next.app.android.feats.login.parts.UnderFormText
import epos_next.app.android.components.theme.ApplicationTheme
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalAnimationApi
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
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
                        }
                    }
                }
            }
        }
    }
}