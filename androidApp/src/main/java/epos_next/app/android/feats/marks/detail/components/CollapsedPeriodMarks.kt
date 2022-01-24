package epos_next.app.android.feats.marks.detail.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import epos_next.app.android.R
import epos_next.app.android.components.theme.contrast
import epos_next.app.android.components.theme.lightPrimary
import epos_next.app.android.components.theme.textPrimary
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@ExperimentalAnimationApi
@Preview
@Composable
fun CollapsedPeriodMarks(text: String = "1 четверть") {
    val isOpen = remember { mutableStateOf(false) }

    Header(text, isOpen.value) { isOpen.value = isOpen.value.not() }
    CollapsedContent(isOpen.value) {
        for (i in 1..5) {
            MarkRow(modifier = Modifier.padding(bottom = 15.dp))
        }

        PrimaryMarkRow(
            modifier = Modifier.padding(bottom = 15.dp),
            name = "Средний балл",
            value = "4.15",
            accent = true
        )
        PrimaryMarkRow(name = "Итоговая оценка", value = "4", accent = true)
    }
}

@Preview(showBackground = true)
@Composable
private fun Header(text: String = "1 четверть", isOpen: Boolean = false, onTap: () -> Unit = {}) {
    val chevronAnimation by animateFloatAsState(if (isOpen) 0f else -90f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onTap() }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text, style = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.textPrimary))

        Image(
            painter = painterResource(id = R.drawable.chevron_icon),
            contentDescription = "chevron icon",
            modifier = Modifier
                .size(18.dp)
                .rotate(chevronAnimation)
        )
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
private fun CollapsedContent(isOpen: Boolean = true, content: @Composable () -> Unit = {}) {
    val enterExpand = remember { expandVertically() }
    val exitCollapsed = remember { shrinkVertically() }

    AnimatedVisibility(visible = isOpen, enter = enterExpand, exit = exitCollapsed) {
        Box(modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)) {
            Column {
                content()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MarkRow(
    modifier: Modifier = Modifier,
    theme: String = "Работа по механике",
    date: LocalDate = LocalDate.now(),
    mark: Int = 4
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Theme name
        Text(theme, style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colors.secondary))
        Spacer(modifier = Modifier.weight(1f))

        // Date
        val dateFormatter = DateTimeFormatter.ofPattern("dd.MM")
        val formattedDate = dateFormatter.format(date)
        Text(
            formattedDate,
            modifier = Modifier.padding(end = 15.dp),
            style = TextStyle(fontSize = 13.sp, color = MaterialTheme.colors.lightPrimary)
        )

        // Mark
        Text(
            mark.toString(),
            style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colors.contrast)
        )
    }
}
