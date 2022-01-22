package epos_next.app.android

import androidx.compose.animation.core.MutableTransitionState
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val scheduleVisible = MutableTransitionState(false).apply { targetState = true }
}