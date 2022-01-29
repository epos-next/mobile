package epos_next.app

import kotlin.time.Duration
import kotlin.time.DurationUnit

fun formatTimeLeft(duration: Duration): String {
    var m = duration.inWholeMinutes.toString()
    var s = (duration.inWholeSeconds - duration.inWholeMinutes * 60).toString()
    
    if (m.length == 1) m = "0$m"
    if (s.length == 1) s = "0$s"

    return "$m:$s"
}