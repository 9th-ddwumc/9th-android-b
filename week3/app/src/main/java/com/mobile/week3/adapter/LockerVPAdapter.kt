package com.mobile.week3.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mobile.week3.viewpager.locker.MusicFileFragment
import com.mobile.week3.viewpager.locker.SavedAlbumFragment
import com.mobile.week3.viewpager.locker.SavedSongFragment

class LockerVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> SavedSongFragment()
            1 -> MusicFileFragment()
            else -> SavedAlbumFragment()
        }
    }
}