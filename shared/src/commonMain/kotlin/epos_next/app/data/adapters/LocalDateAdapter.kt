package epos_next.app.data.adapters

import com.squareup.sqldelight.ColumnAdapter
import kotlinx.datetime.LocalDate

val localDateAdapter = object : ColumnAdapter<LocalDate, String> {
    override fun decode(databaseValue: String): LocalDate {
        return LocalDate.parse(databaseValue)
    }

    override fun encode(value: LocalDate): String {
        return value.toString()
    }
}