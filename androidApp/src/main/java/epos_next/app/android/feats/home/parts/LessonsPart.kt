package epos_next.app.android.feats.home.parts

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import epos_next.app.android.feats.home.HomeViewModel
import epos_next.app.android.components.LessonSkeleton
import epos_next.app.android.feats.home.components.*
import epos_next.app.domain.entities.Lesson
import epos_next.app.state.schedule.ScheduleReducer
import epos_next.app.state.schedule.ScheduleState
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import java.lang.Integer.max
import java.time.ZoneId

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun LessonPart() {
    val scheduleReducer = get<ScheduleReducer>()
    val state = scheduleReducer.state.collectAsState().value

    val viewModel = getViewModel<HomeViewModel>()

    val coroutineScope = rememberCoroutineScope()

    Calendar(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth(),
        onDaySelected = {
            coroutineScope.launch {
                val amount = scheduleReducer.loadDateSchedule(it)
                val kotlinDate =
                    Instant.fromEpochSeconds(
                        viewModel.calendarDate.value.atStartOfDay(ZoneId.systemDefault())
                            .toEpochSecond()
                    )
                        .toLocalDateTime(TimeZone.currentSystemDefault()).date

                if (amount != 0) viewModel.resetScheduleVisible()
                else if (kotlinDate == it) scheduleReducer.loadDateSchedule(it)
            }
        }
    )

    when (state) {
        is ScheduleState.Idle, is ScheduleState.Loading, is ScheduleState.NoLessons -> {
            val lessons = if (state is ScheduleState.Idle) state.lessons else null
            var prevLessons by remember { mutableStateOf(lessons ?: listOf()) }
            if (lessons != null) prevLessons = lessons
            LessonsList(
                lessons?.size ?: max(prevLessons.size, 5),
                lessons ?: prevLessons,
                state
            )
        }

        is ScheduleState.Error -> TextError(Modifier.padding(top = 10.dp), state.message)
        is ScheduleState.ItsSummer -> NowSummerMessage()
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
private fun LessonsList(count: Int, lessons: List<Lesson>, state: ScheduleState) {
    AnimatedList(state) {
        AnimatedTitle(state)

        repeat(count) { i ->
            AnimatedContent(
                targetState = state,
                transitionSpec = {
                    fadeIn(tween(durationMillis = 400)) with fadeOut(tween(durationMillis = 400))
                }
            ) { animatedState ->
                when (animatedState) {
                    is ScheduleState.Idle -> ScheduleLessonComponent(
                        lesson = lessons[i],
                        index = i,
                    )
                    is ScheduleState.Loading -> LessonSkeleton(
                        modifier = Modifier.padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = if (i == 0) 10.dp else 20.dp
                        )
                    )
                    else -> Unit
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
private fun AnimatedList(state: ScheduleState, content: @Composable ColumnScope.() -> Unit) {
    val needPadding = state is ScheduleState.Idle || state is ScheduleState.Loading
    val padding by animateDpAsState(if (needPadding) 25.dp else 0.dp)

    Column(
        modifier = Modifier
            .padding(bottom = padding)
            .animateContentSize(),
        content = content
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
private fun AnimatedTitle(state: ScheduleState) {
    AnimatedContent(
        targetState = state is ScheduleState.Idle || state is ScheduleState.Loading,
        transitionSpec = {
            fadeIn(tween(durationMillis = 400)) with fadeOut(tween(durationMillis = 400))
        }
    ) { show ->
        if (show)
            HomeTitle(
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 20.dp,
                ),
                text = "Уроки"
            )
    }
}