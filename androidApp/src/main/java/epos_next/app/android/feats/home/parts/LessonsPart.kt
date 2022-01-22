package epos_next.app.android.feats.home.parts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import epos_next.app.android.components.LessonSkeletonList
import epos_next.app.android.feats.home.components.*
import epos_next.app.domain.entities.Lesson
import org.koin.androidx.compose.getViewModel

@Composable
fun LessonPart() {
    Calendar(
        modifier = Modifier
            .padding(top = 25.dp)
            .fillMaxWidth()
    )

    HomeTitle(
        modifier = Modifier.padding(
            start = 20.dp,
            end = 20.dp,
            top = 20.dp,
        ),
        text = "Уроки"
    )

    LessonSkeletonList()

//    when (state) {
//        is LessonState.IdleState -> {
//            if (state.lessonSchedule.isNotEmpty()) {
//                LessonsList(state.lessonSchedule)
//            }
//        }
//        is LessonState.ErrorState -> TextError(Modifier.padding(top = 10.dp), state.error)
//        is LessonState.LoadingState -> LessonSkeletonList()
//        is LessonState.ItsSummerState -> NowSummerMessage()
//    }
}

@Composable
private fun LessonsList(lessons: List<Lesson>) {
    for (i in lessons.indices) {
        LessonWithRoomAndTime(
            modifier = Modifier.padding(
                top = if (i == 0) 10.dp else 20.dp, start = 20.dp,
                end = 20.dp,
            ),
            lesson = lessons[i]
        )
    }
}