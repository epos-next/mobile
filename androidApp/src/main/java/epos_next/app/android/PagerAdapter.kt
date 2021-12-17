package epos_next.app.android

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import epos_next.app.android.feats.home.HomeScreenFragment
import epos_next.app.android.feats.marks.MarkScreenFragment
import epos_next.app.android.feats.profile.ProfileScreenFragment

private const val NUM_PAGES = 3

class PagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeScreenFragment()
            1 -> MarkScreenFragment()
            else -> ProfileScreenFragment() // or 2
        }
    }
}
