package epos_next.app.data.adapters

import com.squareup.sqldelight.ColumnAdapter
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

val durationAdapter = object : ColumnAdapter<Duration, Long> {
    override fun decode(databaseValue: Long): Duration {
        return databaseValue.toDuration(DurationUnit.SECONDS)
    }

    override fun encode(value: Duration): Long {
        return value.inWholeSeconds
    }
}