package ddwu.mobile.umc.flo_danu.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ddwu.mobile.umc.flo_danu.vp.locker.MusicFileFragment
import ddwu.mobile.umc.flo_danu.vp.locker.SavedAlbumFragment
import ddwu.mobile.umc.flo_danu.vp.locker.SavedSongFragment

class LockerVPAdapter(fragment: Fragment): FragmentStateAdapter(fragment){
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SavedSongFragment()
            1 -> MusicFileFragment()
            else -> SavedAlbumFragment()
        }
    }

    override fun getItemCount(): Int = 3


}