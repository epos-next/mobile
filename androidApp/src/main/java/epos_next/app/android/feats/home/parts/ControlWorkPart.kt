package epos_next.app.android.feats.home.parts

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import epos_next.app.android.components.LessonSkeletonList
import epos_next.app.android.feats.home.components.ControlWorkComponent
import epos_next.app.android.feats.home.components.TextError
import epos_next.app.android.feats.home.components.TitleWithCreateButton
import epos_next.app.android.lib.collectAsState
import epos_next.app.state.schoolTests.SchoolTestsReducer
import epos_next.app.state.schoolTests.SchoolTestsState
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun ControlWorkPart() {

    val reducer = get<SchoolTestsReducer>()

    TitleWithCreateButton(
        text = "Контрольные работы",
        modifier = Modifier.padding(top = 25.dp)
    )

    when (val state = reducer.collectAsState()) {
        is SchoolTestsState.Idle -> {
            for (e in state.tests) {
                ControlWorkComponent(
                    modifier = Modifier.padding(top = 10.dp),
                    controlWork = e,
                )
            }
        }
        is SchoolTestsState.Error -> TextError(Modifier.padding(top = 10.dp), state.message)
        is SchoolTestsState.Loading -> LessonSkeletonList(count = 3, itemModifier = Modifier)
    }
}