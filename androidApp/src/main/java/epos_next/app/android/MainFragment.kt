package epos_next.app.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment: Fragment() {
    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = view.findViewById(R.id.pager)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = PagerAdapter(requireActivity())

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
        bottomNavigationView = view.findViewById(R.id.bottom_navigation_view)

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
    }
}