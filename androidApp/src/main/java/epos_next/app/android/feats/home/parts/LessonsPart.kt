package epos_next.app.android.feats.home.parts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import epos_next.app.android.MainViewModel
import epos_next.app.android.components.LessonSkeletonList
import epos_next.app.android.feats.home.components.*
import epos_next.app.domain.entities.Lesson
import epos_next.app.state.schedule.ScheduleReducer
import epos_next.app.state.schedule.ScheduleState
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun LessonPart() {

    val scheduleReducer = get<ScheduleReducer>()
    val state = scheduleReducer.state.collectAsState().value

    val viewModel = getViewModel<MainViewModel>()

    Calendar(
        modifier = Modifier
            .padding(top = 25.dp)
            .fillMaxWidth(),
        onDaySelected = {
            scheduleReducer.loadDateSchedule(it)
            viewModel.resetScheduleVisible()
        }
    )

    HomeTitle(
        modifier = Modifier.padding(
            start = 20.dp,
            end = 20.dp,
            top = 20.dp,
        ),
        text = "Уроки"
    )

    when (state) {
        is ScheduleState.Idle -> {
            if (state.lessons.isNotEmpty()) {
                LessonsList(state.lessons)
            }
        }
        is ScheduleState.Error -> TextError(Modifier.padding(top = 10.dp), state.message)
        is ScheduleState.Loading -> LessonSkeletonList()
        is ScheduleState.ItsSummer -> NowSummerMessage()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LessonsList(lessons: List<Lesson>) {
    for (i in lessons.indices) {
        ScheduleLessonComponent(
            lesson = lessons[i],
            index = i,
        )
    }
}