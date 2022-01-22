package epos_next.app.android.feats.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import epos_next.app.state.authStatus.AuthStatusReducer
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ProfileScreenFragment : Fragment() {
    private val authStateReducer: AuthStatusReducer by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val coroutineScope = rememberCoroutineScope()

                fun handleClick() {
                    coroutineScope.launch {
                        authStateReducer.logout()
                    }
                }

                Button(onClick = ::handleClick) {
                    Text("logout")
                }
            }
        }
    }
}