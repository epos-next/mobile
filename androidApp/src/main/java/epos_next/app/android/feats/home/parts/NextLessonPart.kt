package epos_next.app.android.feats.home.parts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import epos_next.app.android.components.LessonSkeleton
import epos_next.app.android.feats.home.components.HomeTitle
import epos_next.app.android.feats.home.components.LessonWithRoomTimeAndTimeLeft
import epos_next.app.android.feats.home.components.LessonWithRoomTimeAndTimeLeftProps

@Composable
fun NextLessonPart() {
    Column(modifier = Modifier.padding(end = 20.dp, start = 20.dp, top = 20.dp)) {
        HomeTitle(text = "Следующий урок")
        Spacer(modifier = Modifier.height(10.dp))
        LessonSkeleton()

//        when (state) {
//            is NextLessonState.Idle -> {
//                HomeTitle(text = "Следующий урок")
//                LessonWithRoomTimeAndTimeLeft(
//                    modifier = Modifier.padding(top = 10.dp),
//                    props = LessonWithRoomTimeAndTimeLeftProps(
//                        lesson = state.nextLesson,
//                        timeLeft = state.timeLeft,
//                    ),
//                )
//
//            }
//            is NextLessonState.Loading -> {
//                HomeTitle(text = "Следующий урок")
//                Spacer(modifier = Modifier.height(10.dp))
//                LessonSkeleton()
//            }
//            is NextLessonState.NoNextLesson -> Unit
//        }
    }
}