package epos_next.app.android.feats.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import epos_next.app.android.components.MainBottomSheetScreen
import epos_next.app.android.feats.home.parts.*

@Composable
fun HomeScreen(scrollState: ScrollState, openSheet: (MainBottomSheetScreen) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(MaterialTheme.colors.background)
            .verticalScroll(scrollState)
    ) {
        NextLessonPart()
        LessonPart()

        Column(
            modifier = Modifier.padding(
                start = 20.dp,
                end = 20.dp,
                bottom = 75.dp
            )
        ) {
            HomeworkPart()
            ControlWorkPart(openSheet)
            AdvertisementPart(openSheet)
        }
    }
}