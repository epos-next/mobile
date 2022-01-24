package epos_next.app.state.marks

import epos_next.app.data.marks.MarksDataSource
import epos_next.app.lib.BaseReducer
import kotlinx.coroutines.flow.update
import org.koin.core.component.inject

class MarksReducer: BaseReducer<MarksState>(MarksState.Loading) {
    private val marksDataSource: MarksDataSource by inject()

    fun loadMarks() {
        val marks = marksDataSource.get()
        stateFlow.update { MarksState.Idle(marks) }
    }
}