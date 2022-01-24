package epos_next.app.android.feats.home

import androidx.compose.animation.core.MutableTransitionState
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    var scheduleVisible = MutableTransitionState(false).apply { targetState = true }

    fun resetScheduleVisible() {
        scheduleVisible = MutableTransitionState(false).apply { targetState = true }
    }
}