package epos_next.app.state.homework

import epos_next.app.data.homework.HomeworkDataSource
import epos_next.app.domain.exceptions.translateException
import epos_next.app.lib.BaseProxyReducer
import epos_next.app.network.Api
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class HomeworkReducer : BaseProxyReducer<HomeworkState>(HomeworkState.Loading) {
    private val homeworkDataSource: HomeworkDataSource by inject()
    private val api: Api by inject()

    override val state = homeworkDataSource.get()
        .catch { e -> HomeworkState.Error(translateException(e)) }
        .map { homework ->
            HomeworkState.Idle(homework.map { it.copy() })
        }
        .catch { e -> HomeworkState.Error(translateException(e)) }

    fun updateDone(id: Long, done: Boolean) = scope.launch {
        try {
            homeworkDataSource.updateDone(id, done)

            if (done) api.completeHomework(id)
            else api.cancelCompleteHomework(id)
        } catch (e: Throwable) {
            Napier.e("error: ", e)
        }
    }
}
