package epos_next.app.android

import android.app.Application
import epos_next.app.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.dsl.module

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            module {}
        )
        Napier.base(DebugAntilog())
    }
}