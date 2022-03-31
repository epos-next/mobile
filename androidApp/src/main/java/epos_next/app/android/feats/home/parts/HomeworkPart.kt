package epos_next.app.android.feats.home.parts

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun HomeworkPart() {
    val reducer = get<HomeworkReducer>()
    val coroutineScope = rememberCoroutineScope()

    when (val state = reducer.collectAsState()) {
        is HomeworkState.Idle -> {
            if (state.homework.isNotEmpty()) {
                HomeTitle(Modifier.padding(top = 20.dp), text = "Домашнее задание")

                for (homework in state.homework) {
                    HomeworkComponent(
                        modifier = Modifier.padding(top = 5.dp),
                        homework = homework,
                        onTap = {
                            coroutineScope.launch {
                                reducer.updateDone(homework.id, it)
                            }
                        }
                    )
                }
            }
        }
        is HomeworkState.Error -> TextError(Modifier.padding(top = 20.dp), state.message)
        is HomeworkState.Loading -> {
            HomeTitle(Modifier.padding(top = 20.dp), text = "Домашнее задание")
            LessonSkeletonList(count = 3) { LessonSkeletonWithCheckbox() }
        }
    }
}