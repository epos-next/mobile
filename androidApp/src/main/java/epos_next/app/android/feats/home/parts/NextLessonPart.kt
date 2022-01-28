package epos_next.app.android.feats.home.parts

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import epos_next.app.android.components.LessonSkeleton
import epos_next.app.android.feats.home.components.HomeTitle
import epos_next.app.android.feats.home.components.LessonWithRoomTimeAndTimeLeft
import epos_next.app.android.feats.home.components.LessonWithRoomTimeAndTimeLeftProps
import epos_next.app.state.nextLesson.NextLessonReducer
import epos_next.app.state.nextLesson.NextLessonState
import org.koin.androidx.compose.get

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NextLessonPart() {
    val reducer = get<NextLessonReducer>()

    val state = reducer.state.collectAsState().value

    AnimatedList(state) {
        AnimatedTitle(state)

        when (state) {
            is NextLessonState.Idle -> {
                AnimatedContent(
                    targetState = state.nextLesson,
                    transitionSpec = {
                        (slideInHorizontally { width -> width } + fadeIn() with
                                slideOutHorizontally { width -> -width } + fadeOut())
                            .using(SizeTransform(clip = false))
                    }
                ) { lesson ->
                    LessonWithRoomTimeAndTimeLeft(
                        modifier = Modifier.padding(top = 10.dp),
                        props = LessonWithRoomTimeAndTimeLeftProps(
                            lesson = lesson,
                            timeLeft = state.timeLeft,
                        ),
                    )
                }


            }
            else -> Unit
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedTitle(state: NextLessonState) {
    AnimatedContent(
        targetState = state::class.simpleName,
        transitionSpec = {
            (slideInHorizontally { width -> width } + fadeIn() with
                    slideOutHorizontally { width -> -width } + fadeOut())
                .using(SizeTransform(clip = false))
        }
    ) { name ->
        if (name == NextLessonState.Break::class.simpleName) HomeTitle(text = "Следующий урок")
        else if(name == NextLessonState.LessonTime::class.simpleName)  HomeTitle(text = "Сейчас идет")
    }

}

@Composable
private fun AnimatedList(state: NextLessonState, content: @Composable ColumnScope.() -> Unit) {
    val visible = state is NextLessonState.Idle
    val padding = animateDpAsState(targetValue = if (visible) 20.dp else 0.dp)
    Column(
        modifier = Modifier
            .padding(end = 20.dp, start = 20.dp, top = padding.value)
            .animateContentSize()
    ) { content() }
}