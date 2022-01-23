package epos_next.app.android.feats.home.parts

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import epos_next.app.android.lib.collectAsState
import epos_next.app.android.components.LessonSkeletonList
import epos_next.app.android.components.LessonSkeletonWithCheckbox
import epos_next.app.android.feats.home.components.HomeTitle
import epos_next.app.android.feats.home.components.HomeworkComponent
import epos_next.app.android.feats.home.components.TextError
import epos_next.app.state.homework.HomeworkReducer
import epos_next.app.state.homework.HomeworkState
import org.koin.androidx.compose.get

@Composable
fun HomeworkPart() {
    val reducer = get<HomeworkReducer>()

    when (val state = reducer.collectAsState()) {
        is HomeworkState.Idle -> {
            if (state.homework.isNotEmpty()) {
                HomeTitle(text = "Домашнее задание")

                for (homework in state.homework) {
                    HomeworkComponent(
                        modifier = Modifier.padding(top = 10.dp),
                        homework = homework,
                        onTap = { reducer.updateDone(homework.id, it) }
                    )
                }
            }
        }
        is HomeworkState.Error -> TextError(Modifier.padding(top = 10.dp), state.message)
        is HomeworkState.Loading -> {
            HomeTitle(text = "Домашнее задание")
            LessonSkeletonList(count = 3) { LessonSkeletonWithCheckbox() }
        }
    }
}