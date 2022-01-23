package epos_next.app.state.homework

import epos_next.app.data.homework.HomeworkDataSource
import epos_next.app.domain.entities.Homework
import epos_next.app.domain.exceptions.translateException
import io.ktor.utils.io.core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeworkReducer : KoinComponent {
    private val homeworkDataSource: HomeworkDataSource by inject()

    val state = homeworkDataSource.get()
        .catch { e -> HomeworkState.Error(translateException(e)) }
        .map { HomeworkState.Idle(it) }

    fun onChange(provideNewState: ((HomeworkState) -> Unit)): Closeable {
        val job = Job()

        state
            .onEach { provideNewState.invoke(it) }
            .launchIn(CoroutineScope(Dispatchers.Main + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}