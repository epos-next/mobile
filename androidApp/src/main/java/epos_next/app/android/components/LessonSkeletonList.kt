package epos_next.app.android.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp

@Composable
fun LessonSkeletonList(
    modifier: Modifier = Modifier,
    count: Int = 5,
    topPadding: Boolean = true,
    itemModifier: Modifier = Modifier.padding(horizontal = 20.dp),
    content: @Composable ColumnScope.() -> Unit = { LessonSkeleton(modifier = itemModifier) },
) {
    Column(modifier = modifier.composed { Modifier.padding(top = 10.dp) }) {
        repeat(count) {
            if (it != 0 && topPadding) Spacer(modifier = Modifier.height(if (it == 0) 15.dp else 25.dp))
            content()
        }
    }

}