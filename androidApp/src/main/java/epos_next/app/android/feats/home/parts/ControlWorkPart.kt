package epos_next.app.android.feats.home.parts

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import epos_next.app.android.components.LessonSkeletonList
import epos_next.app.android.feats.home.components.ControlWorkComponent
import epos_next.app.android.feats.home.components.TextError
import epos_next.app.android.feats.home.components.TitleWithCreateButton
import org.koin.androidx.compose.getViewModel

@Composable
fun ControlWorkPart() {

    TitleWithCreateButton(
        text = "Контрольные работы",
        modifier = Modifier.padding(top = 25.dp)
    )

    LessonSkeletonList(count = 3, itemModifier = Modifier)

//    when (state) {
//        is ControlWorkState.IdleState -> {
//            for (e in state.controlWorks) {
//                ControlWorkComponent(
//                    modifier = Modifier.padding(top = 10.dp),
//                    controlWork = e,
//                )
//            }
//        }
//        is ControlWorkState.ErrorState -> TextError(Modifier.padding(top = 10.dp), state.error)
//        is ControlWorkState.LoadingState -> LessonSkeletonList(count = 3, itemModifier = Modifier)
//    }
}