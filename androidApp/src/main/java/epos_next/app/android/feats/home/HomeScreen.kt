package epos_next.app.android.feats.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import epos_next.app.android.components.MainBottomSheetScreen
import epos_next.app.android.feats.home.parts.*

@Composable
fun HomeScreen(scrollState: ScrollState, openSheet: (MainBottomSheetScreen) -> Unit) {
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        NextLessonPart()
        LessonPart()

        Column(
            modifier = Modifier.padding(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp,
                bottom = 75.dp
            )
        ) {
            HomeworkPart()
            ControlWorkPart(openSheet)
            AdvertisementPart(openSheet)
        }
    }
}