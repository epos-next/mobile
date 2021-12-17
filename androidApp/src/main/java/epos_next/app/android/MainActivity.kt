package epos_next.app.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import epos_next.app.android.feats.login.LoginActivity

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // remove top bar
        supportActionBar?.hide()


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

        finishActivityAndPushToLogin()
    }

    private fun finishActivityAndPushToLogin() {
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        finish()
        startActivity(intent)
    }
}
