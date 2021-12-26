package epos_next.app.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import epos_next.app.android.feats.login.LoginActivity
import epos_next.app.usecases.IsAuthorizedUseCase
import epos_next.app.usecases.UpdateTokenIfNeed
import org.koin.android.ext.android.inject
import kotlin.time.ExperimentalTime

@ExperimentalTime
class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView

    private val isAuthorizedUseCase: IsAuthorizedUseCase by inject()
    private val shouldUpdateAuthTokens: UpdateTokenIfNeed by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // remove top bar
        supportActionBar?.hide()

        Log.d("222", isAuthorizedUseCase.execute().toString())

        setContentView(R.layout.activity_main)

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.pager)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = PagerAdapter(this)

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                bottomNavigationView.selectedItemId = when (position) {
                    0 -> R.id.bottom_navigation_item_home
                    1 -> R.id.bottom_navigation_item_marks
                    else -> R.id.bottom_navigation_item_profile
                }
                super.onPageSelected(position)
            }
        })

        viewPager.adapter = pagerAdapter

        // Instantiate bottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)

        // Set icon tint for active icon changed
        bottomNavigationView.itemIconTintList = null

        // Set the listener for item selection in the bottom navigation view.
        bottomNavigationView.setOnItemSelectedListener {
            viewPager.currentItem = when (it.itemId) {
                R.id.bottom_navigation_item_home -> 0
                R.id.bottom_navigation_item_marks -> 1
                else -> 2
            }

            return@setOnItemSelectedListener true
        }

        if (!isAuthorizedUseCase.execute()) finishActivityAndPushToLogin()
    }

    private suspend fun navigateToLoginActivityIfNeed() {
        if (!isAuthorizedUseCase.execute()) {
            finishActivityAndPushToLogin()
        } else {
            val result = shouldUpdateAuthTokens.execute()

//            result.onFailure {
//                logger.warn { "navigateToLoginActivityIfNeed() - doing redirect to login" }
//                finishActivityAndPushToLogin()
//            }
        }
    }

    private fun finishActivityAndPushToLogin() {
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        finish()
        startActivity(intent)
    }

}
