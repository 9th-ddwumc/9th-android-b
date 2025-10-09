package dduw.com.mobile.week3_gini.locker

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class LockerViewAdaptor(fragment : Fragment): FragmentStateAdapter(fragment) {
    //일단 tab 3개
    override fun getItemCount(): Int {
        return 3;
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> SaveSongFragment()
            1 -> SongFileFragment()
            2 -> SaveAlbumFragment()
            else -> SaveSongFragment()
        }
    }
}