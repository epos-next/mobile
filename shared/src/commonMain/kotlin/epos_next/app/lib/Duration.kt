package epos_next.app.lib

import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun Duration.formatAsTime(): String {
    var h = this.toDouble(DurationUnit.HOURS).toString()
    var m = this.toDouble(DurationUnit.MINUTES).toString()

    if (h.length == 1) h = "0$h"
    if (m.length == 1) m = "0$m"

    return "$h:$m"
}