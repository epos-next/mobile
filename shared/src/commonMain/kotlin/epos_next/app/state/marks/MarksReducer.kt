package epos_next.app.state.marks

import epos_next.app.data.marks.MarksDataSource
import epos_next.app.lib.BaseReducer
import epos_next.app.utils.FormatHelper
import kotlinx.coroutines.flow.update
import org.koin.core.component.inject

class MarksReducer : BaseReducer<MarksState>(MarksState.Loading) {
    private val marksDataSource: MarksDataSource by inject()

    fun loadMarks() {
        val marks = marksDataSource.get()
        stateFlow.update { MarksState.Idle(marks) }
    }

    fun getSubjectNames(): List<String> {
        val state = stateFlow.value
        if (state is MarksState.Idle) {
            val marks = state.marks
            return marks.keys
                .filter { marks[it]!!.periods.isNotEmpty() }
                .map { FormatHelper.formatSubjectName(it) }
                .toList()
        }
        return emptyList()
    }
}