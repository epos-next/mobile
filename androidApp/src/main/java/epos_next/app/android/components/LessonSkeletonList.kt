package epos_next.app.android.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp
import epos_next.app.android.feats.home.components.HomeTitle

@Composable
fun LessonSkeletonList(
    count: Int = 5,
    itemModifier: Modifier = Modifier.padding(horizontal = 20.dp),
    content: @Composable ColumnScope.() -> Unit = { LessonSkeleton(modifier = itemModifier) },
) {
    Column {
        HomeTitle(
            modifier = Modifier.padding(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp,
            ),
            text = "Уроки"
        )
        repeat(count) {
            Spacer(modifier = Modifier.height(if (it == 0) 10.dp else 25.dp))
            content()
        }
    }

}