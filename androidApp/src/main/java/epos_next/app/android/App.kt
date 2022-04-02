package epos_next.app.android

import android.app.Application
import epos_next.app.initKoin
import epos_next.app.initLogger
import org.koin.android.ext.koin.androidContext

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(androidModule) { it.androidContext(this@App) }
        initLogger()
    }
}