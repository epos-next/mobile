package epos_next.app.utils

import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
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

    fun month(month: Month) = months[month.ordinal]

    @OptIn(ExperimentalTime::class)
    fun futureDate(date: LocalDateTime): String {
        val tz = TimeZone.currentSystemDefault()
        val duration: Duration = date.toInstant(tz) - Clock.System.now()
        return if (duration.toDouble(DurationUnit.DAYS) == 0.0) "Сегодня"
        else if (duration.toDouble(DurationUnit.DAYS) < 1) "Завтра"
        else "${date.dayOfMonth} ${monthsRus[date.month.ordinal]}"
    }

    @OptIn(ExperimentalTime::class)
    fun formatLessonTime(startDate: LocalDateTime, duration: Duration): String {
        val tz = TimeZone.currentSystemDefault()
        val endDate = (startDate.toInstant(tz) + duration).toLocalDateTime(tz)
        return "${startDate.formatTime()} — ${endDate.formatTime()}"
    }

    fun formatSubjectName(name: String): String {
        val lowerCase = name.lowercase()
        if (lowerCase.contains("основы безопасности жизнедеятельности")) return "ОБЖ"
        if (lowerCase.contains("изобразительное искусство")) return "ИЗО"
        if (lowerCase.contains("английский язык")) return "Английский язык"
        if (lowerCase.contains("история")) return "История"
        if (lowerCase.contains("физическая культура")) return "Физкультура"
        return name
    }

    fun formatMarkDate(date: LocalDateTime): String {
        var d = date.dayOfMonth.toString()
        var m = date.month.number.toString()

        val tz = TimeZone.currentSystemDefault()
        val todayYear = Clock.System.now().toLocalDateTime(tz).year

        if (d.length == 1) d = "0$d"
        if (m.length == 1) m = "0$m"

        return "$d.$m" + (if(todayYear == date.year) "" else ".${date.year}")
    }
}

fun LocalDateTime.formatTime(): String {
    var h = this.hour.toString()
    var m = this.minute.toString()

    if (h.length == 1) h = "0$h"
    if (m.length == 1) m = "0$m"

    return "$h:$m"
}