package epos_next.app.android.feats.home

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import epos_next.app.android.components.MainBottomSheetScreen
import kotlinx.coroutines.launch
import java.time.LocalDate

class HomeViewModel: ViewModel() {
    var scheduleVisible = MutableTransitionState(false).apply { targetState = true }

    var calendarDate = mutableStateOf(LocalDate.now())

    fun resetScheduleVisible() {
        scheduleVisible = MutableTransitionState(false).apply { targetState = true }
    }
}