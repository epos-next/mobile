package epos_next.app.state.homework

import epos_next.app.data.homework.HomeworkDataSource
import epos_next.app.domain.entities.Homework
import epos_next.app.domain.exceptions.translateException
import epos_next.app.lib.BaseProxyReducer
import io.ktor.utils.io.core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeworkReducer : BaseProxyReducer<HomeworkState>(HomeworkState.Loading), KoinComponent {
    private val homeworkDataSource: HomeworkDataSource by inject()

    override val state = homeworkDataSource.get()
        .catch { e -> HomeworkState.Error(translateException(e)) }
        .map { HomeworkState.Idle(it) }

    fun updateDone(id: Long, done: Boolean) = homeworkDataSource.updateDone(id, done)
}