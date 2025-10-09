package dduw.com.mobile.week3_gini.album

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class AlbumViewAdaptor(fragment : Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3;
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> SongListFragment()
            1 -> DetailFragment()
            2 -> VideoFragment()
            else -> SongListFragment()
        }
    }
}