package epos_next.app.android

import android.app.Application
import epos_next.app.data.dataModule
import org.kodein.di.DI
import org.kodein.di.DIAware

class ThisApplication: Application(), DIAware {
    override val di by DI.lazy {
        import(dataModule)
    }
}