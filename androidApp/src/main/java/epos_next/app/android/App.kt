package epos_next.app.android

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import epos_next.app.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(
            module {}
        ) { it.androidContext(this@App) }
        Napier.base(DebugAntilog())
    }
}