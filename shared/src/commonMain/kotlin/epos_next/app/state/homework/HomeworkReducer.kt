package epos_next.app.state.homework

import epos_next.app.data.homework.HomeworkDataSource
import epos_next.app.domain.entities.Homework
import epos_next.app.domain.exceptions.translateException
import epos_next.app.lib.BaseProxyReducer
import epos_next.app.network.Api
import io.ktor.utils.io.core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeworkReducer : BaseProxyReducer<HomeworkState>(HomeworkState.Loading) {
    private val homeworkDataSource: HomeworkDataSource by inject()
    private val api: Api by inject()

    override val state = homeworkDataSource.get()
        .catch { e -> HomeworkState.Error(translateException(e)) }
        .map { HomeworkState.Idle(it) }

    fun updateDone(id: Long, done: Boolean) = scope.launch {
        homeworkDataSource.updateDone(id, done)

        if (done) api.completeHomework(id)
        else api.cancelCompleteHomework(id)
    }
}