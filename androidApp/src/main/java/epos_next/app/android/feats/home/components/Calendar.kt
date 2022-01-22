package epos_next.app.android.feats.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import epos_next.app.android.helpers.FormatHelper
import epos_next.app.android.components.Grid
import epos_next.app.android.components.theme.lightPrimary
import epos_next.app.android.components.theme.textPrimary
import epos_next.app.utils.weekDays
import java.time.LocalDate
import java.time.Month
import epos_next.app.android.R

@Preview
@Composable
fun Calendar(
    modifier: Modifier = Modifier.fillMaxWidth(),
    onDaySelected: (date: LocalDate) -> Any = {}
) {
    // State
    val date = remember { mutableStateOf(LocalDate.now()) }
    val dates = daysInMonthArray(date.value)

    // handlers
    fun onDaySelectedHandler(day: LocalDate) {
        date.value = day
        onDaySelected(day)
    }

    Box(modifier = modifier) {
        Column {

            CalendarHeader(date.value, onDateChanged = { onDaySelectedHandler(it) })

            WeekdayRow()

            Grid(
                list = dates,
                cols = 7,
                rowModifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                colModifier = Modifier.padding(top = 20.dp)
            ) { item ->
                CalendarItem(
                    modifier = Modifier.weight(1f),
                    item = item,
                    onDaySelected = { onDaySelectedHandler(item.date) }
                )
            }
        }

    }
}

private data class Item(
    val active: Boolean,
    val date: LocalDate,
    val isSelected: Boolean,
)

private fun daysInMonthArray(date: LocalDate): List<Item> {
    val dates = mutableListOf<Item>()
    val firstDay = date.withDayOfMonth(1)
    val year = date.year
    val month = date.month

    // before
    val need = firstDay.dayOfWeek.value
    val prevMonth = if (date.month == Month.JANUARY) {
        LocalDate.of(date.year - 1, 12, date.dayOfMonth)
    } else date.minusMonths(1)
    val dayInLastMonth = prevMonth.lengthOfMonth()
    for (i in (dayInLastMonth - need + 2)..dayInLastMonth) {
        dates.add(
            Item(
                false, LocalDate.of(prevMonth.year, prevMonth.month, i), false
            )
        )
    }

    // This month
    val dayAmount = date.lengthOfMonth()
    for (i in 1..dayAmount) {
        val thisDate = LocalDate.of(year, month, i)
        dates.add(Item(true, thisDate, thisDate == date))
    }

    // After
    val lastDay = dates[dates.size - 1].date
    val needAfter = 7 - lastDay.dayOfWeek.value
    val nextMonth = if (date.month == Month.FEBRUARY) {
        LocalDate.of(date.year + 1, 1, date.dayOfMonth)
    } else date.plusMonths(1)
    for (i in 1..needAfter) {
        dates.add(
            Item(
                false, LocalDate.of(nextMonth.year, nextMonth.month, i), false
            )
        )
    }

    return dates
}

@Composable
fun WeekdayRow() {
    val modifier = Modifier
        .padding(top = 10.dp)
        .fillMaxWidth()

    Row(modifier = modifier, Arrangement.SpaceBetween) {
        for (weekDay in weekDays) {
            Text(
                weekDay,
                modifier = Modifier.weight(1f),
                style = TextStyle(
                    color = MaterialTheme.colors.secondary,
                    fontSize = 15.sp
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun CalendarHeader(date: LocalDate, onDateChanged: (date: LocalDate) -> Any) {
    fun onMothGoForward() {
        onDateChanged(
            if (date.month.value == 12) {
                LocalDate.of(date.year + 1, 1, date.dayOfMonth)
            } else {
                date.plusMonths(1)
            }
        )
    }

    fun onMonthGoBackward() {
        onDateChanged(
            if (date.month.value == 1) {
                LocalDate.of(date.year - 1, 12, date.dayOfMonth)
            } else {
                date.minusMonths(1)
            }
        )
    }

    @Composable
    fun getIconModifier(onClick: () -> Unit) = Modifier
        .size(18.dp)
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) { onClick() }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.left_icon),
            contentDescription = "left icon",
            modifier = getIconModifier { onMonthGoBackward() })

        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "${FormatHelper.month(date.month)} ${date.year}",
            style = TextStyle(color = MaterialTheme.colors.textPrimary, fontSize = 16.sp)
        )
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.right_icon),
            contentDescription = "right icon",
            modifier = getIconModifier { onMothGoForward() }
        )
    }
}

@Composable
private fun CalendarItem(
    modifier: Modifier = Modifier,
    item: Item,
    onDaySelected: () -> Any
) {
    val boxModifier = Modifier
        .composed { modifier }
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) { onDaySelected() }

    Box(modifier = boxModifier) {
        InnerCalendarCell(modifier = Modifier.align(Alignment.Center), item = item)
    }
}

@Composable
private fun InnerCalendarCell(modifier: Modifier = Modifier, item: Item) {
    val itemModifier = if (item.isSelected) Modifier
        .size(34.dp)
        .clip(CircleShape)
        .background(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFF949EFD),
                    Color(0xFF656DFD),
                ),
            )
        )
    else Modifier
        .size(34.dp)
        .clip(CircleShape)

    Box(modifier = itemModifier.composed { modifier }) {
        Text(
            item.date.dayOfMonth.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center),
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                color = when {
                    item.isSelected -> Color.White
                    item.active -> MaterialTheme.colors.textPrimary
                    else -> MaterialTheme.colors.lightPrimary
                }
            )
        )
    }
}