package epos_next.app.android.feats.home.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import epos_next.app.android.feats.home.HomeViewModel
import epos_next.app.domain.entities.Lesson
import org.koin.androidx.compose.get

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ScheduleLessonComponent(
    lesson: Lesson,
    index: Int = 0,
) {
    val viewModel = get<HomeViewModel>()

    val density = LocalDensity.current

    val slideAnimation = slideInHorizontally(
        animationSpec = tween(durationMillis = 400, delayMillis = index * 50)
    ) { with(density) { 100.dp.roundToPx() } }

    val fadeAnimation = fadeIn(
        animationSpec = tween(durationMillis = 400, delayMillis = index * 50)
    )

    AnimatedVisibility(
        visibleState = viewModel.scheduleVisible,
        enter = slideAnimation + fadeAnimation,
        exit = slideOutHorizontally { with(density) { -50.dp.roundToPx() } } + fadeOut()) {
        LessonWithRoomTimeAndMark(
            modifier = Modifier.padding(
                top = if (index == 0) 10.dp else 20.dp, start = 20.dp,
                end = 20.dp,
            ),
            lesson,
        )
    }
}