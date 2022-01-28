package epos_next.app.android.helpers

import kotlinx.datetime.*
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime


object FormatHelper {
    private val months = listOf(
        "Январь",
        "Февраль",
        "Март",
        "Апрель",
        "Май",
        "Июнь",
        "Июль",
        "Август",
        "Сентябрь",
        "Октябрь",
        "Ноябрь",
        "Декабрь"
    )

    private val monthsRus = listOf(
        "Января",
        "Февраля",
        "Марта",
        "Апреля",
        "Мая",
        "Июня",
        "Июля",
        "Августа",
        "Сентября",
        "Октября",
        "Ноября",
        "Декабря"
    )

    fun month(month: Month) = months[month.value - 1]

    @OptIn(ExperimentalTime::class)
    fun futureDate(date: LocalDateTime): String {
        val tz = TimeZone.currentSystemDefault()
        val duration: Duration = date.toInstant(tz) - Clock.System.now()
        return if (duration.toDouble(DurationUnit.DAYS) == 0.0) "Сегодня"
        else if (duration.toDouble(DurationUnit.DAYS) < 1) "Завтра"
        else "${date.dayOfMonth} ${monthsRus[date.month.value - 1]}"
    }
}