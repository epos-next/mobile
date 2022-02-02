package epos_next.app.state.schoolTests

import epos_next.app.data.controlWork.ControlWorkDataSource
import epos_next.app.domain.entities.ControlWork
import epos_next.app.domain.exceptions.translateException
import epos_next.app.lib.BaseProxyReducer
import epos_next.app.network.Api
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import org.koin.core.component.inject

class SchoolTestsReducer : BaseProxyReducer<SchoolTestsState>(SchoolTestsState.Loading) {
    private val controlWorkDataSource: ControlWorkDataSource by inject()
    private val api: Api by inject()

    override val state: Flow<SchoolTestsState> = controlWorkDataSource
        .get()
        .catch { e -> SchoolTestsState.Error(translateException(e)) }
        .map {
            if (it.isEmpty()) SchoolTestsState.NoSchoolTests
            else SchoolTestsState.Idle(it)
        }

    fun createSchoolTest(name: String, subject: String, date: LocalDateTime) = scope.launch {
        try {
            // create new entity
            val controlWork = ControlWork(id = -1, name = name, lesson = subject, date = date)

            // push new entity to cache to show it to user even if it has no id yet
            controlWorkDataSource.cacheOne(controlWork)

            // push entity to api to get id of created entity
            api.createControlWork(controlWork).fold(
                { Napier.e("failed to create school test", it) },
                { id ->
                    // replace fake if of entity to real one
                    controlWorkDataSource.replaceFakeIdWithReal(name, id)
                }
            )
        } catch (e: Throwable) {
            Napier.e("Failed to create school test", e, tag = "State")
        }
    }
}