package epos_next.app.android

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class DarkModeViewModel: ViewModel() {

    var isDarkMode = MutableStateFlow(false)

    fun changeDarkModeTo(value: Boolean) {
        isDarkMode.value = value
    }
}