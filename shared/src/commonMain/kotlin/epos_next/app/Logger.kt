package epos_next.app

import co.touchlab.kermit.Logger
import co.touchlab.kermit.crashlytics.CrashlyticsLogWriter

fun initLogger() {
    Logger.addLogWriter(CrashlyticsLogWriter())
}