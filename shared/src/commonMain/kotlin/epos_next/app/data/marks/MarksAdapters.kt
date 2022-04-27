package epos_next.app.data.marks

import com.squareup.sqldelight.ColumnAdapter
import epos_next.app.domain.entities.MarkUnitPeriods
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val marksAdapter = object : ColumnAdapter<List<MarkUnitPeriods>, String> {
    override fun decode(databaseValue: String): List<MarkUnitPeriods> {
        if (databaseValue.isEmpty()) return listOf()
        return databaseValue
            .split("(splitter)")
            .map { Json.decodeFromString(MarkUnitPeriods.serializer(), it) }
            .sortedBy { it.all.firstOrNull()?.date }
    }

    override fun encode(value: List<MarkUnitPeriods>): String {
        return value.joinToString("(splitter)") { Json.encodeToString(it) }
    }
}