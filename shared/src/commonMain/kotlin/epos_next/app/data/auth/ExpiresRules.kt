package epos_next.app.data.auth

import kotlin.time.DurationUnit
import kotlin.time.toDuration

object ExpiresRules {
    val access = 10.toDuration(DurationUnit.MINUTES)
    val refresh = 365.toDuration(DurationUnit.DAYS)
}