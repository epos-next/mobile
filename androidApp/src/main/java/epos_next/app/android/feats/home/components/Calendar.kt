package epos_next.app.android.feats.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import epos_next.app.android.components.Grid
import epos_next.app.android.components.theme.lightPrimary
import epos_next.app.android.components.theme.textPrimary
import epos_next.app.utils.weekDays
import java.time.LocalDate
import kotlinx.datetime.LocalDate as KotlinLocalDate
import java.time.Month
import epos_next.app.android.R
import epos_next.app.android.feats.home.HomeViewModel
import epos_next.app.utils.FormatHelper
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.androidx.compose.get
import java.time.ZoneId
import kotlin.math.ceil

@Preview
@Composable
fun Calendar(
    modifier: Modifier = Modifier.fillMaxWidth(),
    onDaySelected: (date: KotlinLocalDate) -> Any = {}
) {
    // State
    val date = get<HomeViewModel>().calendarDate
    val dates = daysInMonthArray(date.value)

    // handlers
    fun onDaySelectedHandler(day: LocalDate) {
        date.value = day
        val kotlinDate =
            Instant.fromEpochSeconds(day.atStartOfDay(ZoneId.systemDefault()).toEpochSecond())
                .toLocalDateTime(TimeZone.currentSystemDefault()).date
        onDaySelected(kotlinDate)
    }

    Box(modifier = modifier) {
        SelectedDateIndicator(dates.selectedIndex)

        Column {

            CalendarHeader(date.value, onDateChanged = { onDaySelectedHandler(it) })

            WeekdayRow()

            Grid(
                list = dates.items,
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

@Composable
private fun SelectedDateIndicator(index: Int) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val colSpace = (screenWidth - 22 - (7 * 34)).toDouble() / 6

    val x = animateDpAsState(
        ((if (index % 7 == 0) 6 else index % 7 - 1) * (34 + colSpace)).dp + 11.5.dp,
        animationSpec = tween(250)
    )
    val y = animateDpAsState(
        (ceil(index.toDouble() / 7) * (if (index > 21) 44.0 else 43.75)).dp + 24.dp,
        animationSpec = tween(250)
    )

    Box(
        modifier = Modifier
            .offset(x.value, y.value)
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
    )
}

private data class Item(
    val active: Boolean,
    val date: LocalDate,
    val isSelected: Boolean,
)

private data class CalendarData(
    val items: List<Item>,
    val selectedIndex: Int,
)

private fun daysInMonthArray(date: LocalDate): CalendarData {
    val dates = mutableListOf<Item>()
    val firstDay = date.withDayOfMonth(1)
    val year = date.year
    val month = date.month

    var selectedIndex = 0

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
        selectedIndex++
    }

    // This month
    val dayAmount = date.lengthOfMonth()
    for (i in 1..dayAmount) {
        val thisDate = LocalDate.of(year, month, i)
        dates.add(Item(true, thisDate, thisDate == date))
        if (thisDate == date) selectedIndex += i
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

    return CalendarData(
        items = dates,
        selectedIndex = selectedIndex,
    )
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
                    color = if (MaterialTheme.colors.isLight) MaterialTheme.colors.secondary else Color(
                        0xFF999EA4
                    ),
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
    val boxModifier = modifier
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

    val textColor = animateColorAsState(
        targetValue = when {
            item.isSelected -> Color.White
            item.active -> if (MaterialTheme.colors.isLight) MaterialTheme.colors.textPrimary else Color(0xFFD0D2D5)
            else -> if (MaterialTheme.colors.isLight) MaterialTheme.colors.lightPrimary else Color(0xFF676870)
        },
        animationSpec = tween(250)
    )

    val itemModifier = modifier
        .size(34.dp)
        .clip(CircleShape)
//        .background(
//            brush = Brush.linearGradient(
//                colors = listOf(
//                    gradientColorStart.value,
//                    gradientColorEnd.value,
//                ),
//            )
//        )
//    else Modifier
//        .size(34.dp)
//        .clip(CircleShape)

    Box(modifier = itemModifier) {
        Text(
            item.date.dayOfMonth.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center),
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                color = textColor.value
            )
        )
    }
}