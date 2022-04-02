package epos_next.app.android.feats.home.parts

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import epos_next.app.android.components.LessonSkeletonList
import epos_next.app.android.components.MainBottomSheetScreen
import epos_next.app.android.feats.home.components.ControlWorkComponent
import epos_next.app.android.feats.home.components.TextError
import epos_next.app.android.feats.home.components.TitleWithCreateButton
import epos_next.app.android.lib.collectAsState
import epos_next.app.state.schoolTests.SchoolTestsReducer
import epos_next.app.state.schoolTests.SchoolTestsState
import org.koin.androidx.compose.get

@Composable
fun ControlWorkPart(openBottomSheet: (MainBottomSheetScreen) -> Unit) {

    val reducer = get<SchoolTestsReducer>()

    TitleWithCreateButton(
        text = "Контрольные работы",
        modifier = Modifier.padding(top = 25.dp),
        onTap = {
            Firebase.analytics.logEvent("open_sheet") {
                param(FirebaseAnalytics.Param.ITEM_NAME, "Open Tests Bottom Sheet")
            }

            openBottomSheet(MainBottomSheetScreen.ControlWork)
        }
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