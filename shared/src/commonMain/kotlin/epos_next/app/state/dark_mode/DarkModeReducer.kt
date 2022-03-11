package epos_next.app.state.dark_mode

import com.russhwolf.settings.get
import com.russhwolf.settings.set
import epos_next.app.data.settings
import epos_next.app.lib.BaseReducer
import kotlinx.coroutines.flow.update

class DarkModeReducer: BaseReducer<Boolean>(false) {

    init {
        val isDark: Boolean = settings["is-dark-mode-enabled"] ?: false
        stateFlow.update { isDark }
    }

    fun change(isDark: Boolean) {
        stateFlow.update { isDark }
        settings["is-dark-mode-enabled"] = isDark
    }
}