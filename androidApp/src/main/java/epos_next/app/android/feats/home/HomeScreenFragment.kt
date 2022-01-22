package epos_next.app.android.feats.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import epos_next.app.android.components.theme.ApplicationTheme
import epos_next.app.android.feats.home.parts.*

class HomeScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ApplicationTheme {
                    Scaffold {
                        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                            NextLessonPart()
                            LessonPart()

                            Column(
                                modifier = Modifier.padding(
                                    start = 20.dp,
                                    end = 20.dp,
                                    top = 20.dp,
                                    bottom = 75.dp
                                )
                            ) {
                                HomeworkPart()
                                ControlWorkPart()
                                AdvertisementPart()
                            }
                        }
                    }
                }
            }
        }
    }
}