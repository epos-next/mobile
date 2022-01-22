package epos_next.app.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import epos_next.app.android.components.theme.skeleton
import kotlin.random.Random

@Composable
fun LessonSkeleton(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
    ) {
        Circle()
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
        ) {
            TextSkeleton(generateTitleWidthMultiplier())
            Spacer(modifier = Modifier.height(5.dp))
            TextSkeleton(generateSubtitleWidthMultiplier())
        }
        Spacer(modifier = Modifier.weight(1.0f))
        content()
    }

}

private fun generateTitleWidthMultiplier() = Random.nextDouble(from = 0.3, until = 0.4).toFloat()
private fun generateSubtitleWidthMultiplier() =
    Random.nextDouble(from = 0.5, until = 0.65).toFloat()

@Composable
fun LessonSkeletonWithCheckbox(modifier: Modifier = Modifier) {
    LessonSkeleton(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(shape = RoundedCornerShape(5.dp))
                .background(MaterialTheme.colors.skeleton),
        )
    }
}

@Composable
private fun Circle() {
    Box(
        modifier = Modifier
            .padding(end = 15.dp)
            .size(36.dp)
            .clip(CircleShape)
            .background(Color(0xFFF6F5F9))
    )
}

@Composable
fun TextSkeleton(widthMultiplier: Float) {
    Box(
        modifier = Modifier
            .height(14.dp)
            .fillMaxWidth(widthMultiplier)
            .clip(shape = RoundedCornerShape(2.dp))
            .background(MaterialTheme.colors.skeleton),
    )
}