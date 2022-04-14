package epos_next.app

import co.touchlab.kermit.ExperimentalKermitApi
import co.touchlab.kermit.Logger
import co.touchlab.kermit.crashlytics.CrashlyticsLogWriter

@OptIn(ExperimentalKermitApi::class)
actual fun initLogger() {
    Logger.addLogWriter(CrashlyticsLogWriter())
}