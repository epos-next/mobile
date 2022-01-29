package epos_next.app

import kotlin.time.Duration
import kotlin.time.DurationUnit

fun formatTimeLeft(duration: Duration): String {
    var h = duration.toInt(DurationUnit.HOURS).toString()
    var m = duration.toInt(DurationUnit.MINUTES).toString()

    if (h.length == 1) h = "0$h"
    if (m.length == 1) m = "0$m"

    return "$h:$m"
}