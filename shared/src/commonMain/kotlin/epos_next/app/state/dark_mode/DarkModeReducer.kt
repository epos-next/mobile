package epos_next.app.state.dark_mode

import epos_next.app.lib.BaseReducer
import kotlinx.coroutines.flow.update

class DarkModeReducer: BaseReducer<Boolean>(false) {

    fun change(isDark: Boolean) {
        stateFlow.update { isDark }
    }
}