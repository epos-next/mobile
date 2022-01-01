package epos_next.app.domain.entities

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

typealias Marks = Map<String, MarkUnit>

@Serializable
data class MarkUnit(
    val periods: List<MarkUnitPeriods>,
    val total: Int?
)

@Serializable
data class MarkUnitPeriods(
    val all: List<AllMarkUnitPeriods>,
    val total: Int?,
)

@Serializable
data class AllMarkUnitPeriods(
    val value: Int,
    val date: LocalDateTime,
    val topic: String,
    val number: String,
)




















