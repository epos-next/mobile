package epos_next.app.android.feats.marks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import epos_next.app.android.feats.marks.detail.MarksDetailScreen
import epos_next.app.android.feats.marks.main.MarksScreen

class MarkScreenFragment : Fragment() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val navController = rememberAnimatedNavController()

                AnimatedNavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        MarksScreen(navController)
                    }

                    composable(
                        "detail",
                        enterTransition = {
                            slideInHorizontally(
                                initialOffsetX = { it },
                                animationSpec = tween(400)
                            )
                        },
                        exitTransition = {
                            slideOutHorizontally(
                                targetOffsetX = { it },
                                animationSpec = tween(400)
                            )
                        }

                    ) {
                        MarksDetailScreen(navController)
                    }
                }
            }
        }
    }
}