package com.umc_9th.jini

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class AlbumVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    // 탭의 총 개수
    override fun getItemCount(): Int = 3

    // 각 탭의 위치(position)에 따라 다른 프래그먼트를 생성
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SongFragment()    // 첫 번째 탭: 수록곡
            1 -> DetailFragment() // 두 번째 탭: 상세정보
            2 -> VideoFragment()  // 세 번째 탭: 영상
            else -> SongFragment()
        }
    }
}