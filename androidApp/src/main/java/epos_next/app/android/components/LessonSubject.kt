package epos_next.app.android.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import epos_next.app.android.components.theme.textPrimary
import epos_next.app.android.helpers.UiHelper

@Composable
fun LessonSubject(modifier: Modifier = Modifier, text: String) {
    Box(modifier = modifier) {
        Text(
            text = UiHelper.formatSubjectName(text),
            style = TextStyle(
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                color = MaterialTheme.colors.textPrimary,
            )
        )
    }
}