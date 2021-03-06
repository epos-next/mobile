package epos_next.app.android.feats.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import epos_next.app.android.components.LessonCircle
import epos_next.app.android.components.LessonSubject
import epos_next.app.android.components.LessonSubtitle
import epos_next.app.android.components.theme.lightPrimary
import epos_next.app.domain.entities.Homework
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

private val defaultHomework = Homework(
    id = 1,
    lesson = "География",
    content = "Повторить карту",
    done = true,
    date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
)

@Preview
@Composable
fun HomeworkComponent(
    modifier: Modifier = Modifier,
    homework: Homework = defaultHomework,
    onTap: (status: Boolean) -> Unit = {},
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() })
            { onTap(!homework.done) },
    ) {
        LessonCircle(subject = homework.lesson)

        Column(modifier = Modifier.padding(start = 15.dp).weight(1f)) {
            LessonSubject(text = homework.lesson)
            LessonSubtitle(text = homework.content)
        }

        Checkbox(
            checked = homework.done,
            onCheckedChange = { onTap(it) },
            modifier = modifier.offset(12.dp),
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF68D676),
                uncheckedColor = MaterialTheme.colors.lightPrimary
            )
        )

    }
}