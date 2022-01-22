package epos_next.app.android.feats.home.parts

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import epos_next.app.android.components.LessonSkeletonList
import epos_next.app.android.components.LessonSkeletonWithCheckbox
import epos_next.app.android.feats.home.components.HomeTitle
import epos_next.app.android.feats.home.components.HomeworkComponent
import epos_next.app.android.feats.home.components.TextError

@Composable
fun HomeworkPart() {
    HomeTitle(text = "Домашнее задание", modifier = Modifier.padding(top = 25.dp))
    LessonSkeletonList(count = 3) { LessonSkeletonWithCheckbox() }
//    when (state) {
//        is HomeworkState.IdleState -> {
//            if (state.homework.isNotEmpty()) {
//                HomeTitle(text = "Домашнее задание", modifier = Modifier.padding(top = 25.dp))
//
//                for (homework in state.homework) {
//                    HomeworkComponent(
//                        modifier = Modifier.padding(top = 10.dp),
//                        homework = homework,
//                        onTap = {
//                            viewModel.handleHomeworkComplete(homework.id)
//                        }
//                    )
//                }
//            }
//        }
//        is HomeworkState.ErrorState -> TextError(Modifier.padding(top = 10.dp), state.error)
//        is HomeworkState.LoadingState -> {
//            HomeTitle(text = "Домашнее задание", modifier = Modifier.padding(top = 25.dp))
//            LessonSkeletonList(count = 3) { LessonSkeletonWithCheckbox() }
//        }
//    }
}