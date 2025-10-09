package com.mobile.week4.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mobile.week4.viewpager.album.DetailFragment
import com.mobile.week4.viewpager.album.SongFragment
import com.mobile.week4.viewpager.album.VideoFragment

class AlbumVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> SongFragment()
            1 -> DetailFragment()
            else -> VideoFragment()
        }
    }

}