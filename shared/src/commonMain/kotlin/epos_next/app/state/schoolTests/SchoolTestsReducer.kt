package epos_next.app.state.schoolTests

import epos_next.app.data.controlWork.ControlWorkDataSource
import epos_next.app.domain.exceptions.translateException
import epos_next.app.lib.BaseProxyReducer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.koin.core.component.inject

class SchoolTestsReducer : BaseProxyReducer<SchoolTestsState>(SchoolTestsState.Loading) {
    private val controlWorkDataSource: ControlWorkDataSource by inject()

    override val state: Flow<SchoolTestsState> = controlWorkDataSource
        .get()
        .catch { e -> SchoolTestsState.Error(translateException(e)) }
        .map {
            if (it.isEmpty()) SchoolTestsState.NoSchoolTests
            else SchoolTestsState.Idle(it)
        }
}