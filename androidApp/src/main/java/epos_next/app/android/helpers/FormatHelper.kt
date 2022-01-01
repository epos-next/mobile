package epos_next.app.android.helpers

import kotlinx.datetime.*
import kotlin.time.Duration


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

    fun futureDate(date: LocalDateTime): String {
        val tz = TimeZone.currentSystemDefault()
        val now = Clock.System.now().toLocalDateTime(tz)
        val duration: Duration = Clock.System.now() - date.toInstant(tz)
        return if (duration.inWholeDays <= 1 && now.dayOfMonth == date.dayOfMonth) "Сегодня"
        else if (duration.inWholeDays <= 1) "Завтра"
        else "${date.dayOfMonth} ${monthsRus[date.month.value - 1]}"
    }
}