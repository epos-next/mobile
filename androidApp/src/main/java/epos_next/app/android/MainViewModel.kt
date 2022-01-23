package epos_next.app.android

import androidx.compose.animation.core.MutableTransitionState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

class MainViewModel: ViewModel() {
    var scheduleVisible = MutableTransitionState(false).apply { targetState = true }

    fun resetScheduleVisible() {
        scheduleVisible = MutableTransitionState(false).apply { targetState = true }
    }
}