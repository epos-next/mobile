package epos_next.app.utils

import kotlinx.datetime.*

data class Item(
    val active: Boolean,
    val date: LocalDate,
    val isSelected: Boolean,
)

data class CalendarData(
    val items: List<Item>,
    val selectedIndex: Int,
)

fun daysInMonthArray(date: LocalDate): CalendarData {
    val dates = mutableListOf<Item>()
    val firstDay = date.startOfMonth()
    val year = date.year
    val month = date.month

    var selectedIndex = 0

    // before
    val need = firstDay.dayOfWeek.isoDayNumber
    val prevMonth = if (date.month == Month.JANUARY) {
        LocalDate(date.year - 1, 12, date.dayOfMonth)
    } else date.minus(1, DateTimeUnit.MONTH)
    val dayInLastMonth = prevMonth.lengthOfMonth()
    for (i in (dayInLastMonth - need + 2)..dayInLastMonth) {
        dates.add(
            Item(
                active = false,
                date = LocalDate(prevMonth.year, prevMonth.month, i),
                isSelected = false
            )
        )
        selectedIndex++
    }

    // This month
    val dayAmount = date.lengthOfMonth()
    for (i in 1..dayAmount) {
        val thisDate = LocalDate(year, month, i)
        dates.add(Item(true, thisDate, thisDate == date))
        if (thisDate == date) selectedIndex += i
    }

    // After
    val lastDay = dates[dates.size - 1].date
    val needAfter = 7 - lastDay.dayOfWeek.isoDayNumber
    val nextMonth = if (date.month == Month.FEBRUARY) {
        LocalDate(date.year + 1, 1, date.dayOfMonth)
    } else date.plus(1, DateTimeUnit.MONTH)
    for (i in 1..needAfter) {
        dates.add(
            Item(
                active = false,
                date = LocalDate(nextMonth.year, nextMonth.month, i),
                isSelected = false
            )
        )
    }

    return CalendarData(
        items = dates,
        selectedIndex = selectedIndex,
    )
}

fun LocalDate.startOfMonth(): LocalDate {
    return LocalDate(year = this.year, month = this.month, dayOfMonth = 1)
}

fun LocalDate.lengthOfMonth(): Int {
    val month = this.month.number

    return if (month != 2) {
        31 - (((month - 1) % 7) % 2)
    } else {
        28 + (if (year % 4 == 0) 1 else 0) - (if (year % 100 == 0) 1 else 0) + (if (year % 400 == 0) 1 else 0)
    }
}