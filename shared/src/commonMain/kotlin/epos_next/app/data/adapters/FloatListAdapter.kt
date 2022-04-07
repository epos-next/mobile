package epos_next.app.data.adapters

import com.squareup.sqldelight.ColumnAdapter

val floatListAdapter = object : ColumnAdapter<List<Float>, String> {
    override fun decode(databaseValue: String): List<Float> {
        if (databaseValue.isEmpty()) return emptyList()
        return databaseValue.split(';').map { it.toFloat() }
    }

    override fun encode(value: List<Float>): String {
        return value.joinToString(";")
    }

}