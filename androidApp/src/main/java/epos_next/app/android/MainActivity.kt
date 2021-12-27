package epos_next.app.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import epos_next.app.state.authStatus.AuthStatusReducer
import epos_next.app.state.authStatus.AuthStatusState
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.android.inject
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.flow.collect


@ExperimentalTime
@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val authStatusReducer: AuthStatusReducer by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        // remove top bar
        supportActionBar?.hide()

        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        lifecycleScope.launchWhenStarted {
            authStatusReducer.state.collect {
                when (it) {
                    is AuthStatusState.Authorized -> navController.navigate(R.id.mainFragment)
                    is AuthStatusState.NotAuthorized -> navController.navigate(R.id.loginFragment)
                }
            }
        }
    }
}
