package com.moblie.umc_9th

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.moblie.umc_9th.databinding.FragmentAlbumBinding

class AlbumFragment: Fragment() {
    lateinit var binding: FragmentAlbumBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)

        // AlbumVPAdapter 초기화 및 Fragment 추가
        val albumAdapter = AlbumVPAdapter(this)
        albumAdapter.addFragment(SongListFragment()) // "수록곡" Fragment 추가
        albumAdapter.addFragment(DetailFragment())   // "상세정보" Fragment 추가
        albumAdapter.addFragment(VideoFragment())    // "영상" Fragment 추가

        // ViewPager2에 Adapter 연결
        binding.albumContentVp.adapter = albumAdapter
        // ViewPager2의 방향 설정 (수평 스크롤)
        binding.albumContentVp.orientation = androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL

        // TabLayout과 ViewPager2를 연결
        TabLayoutMediator(binding.albumContentTabLayout, binding.albumContentVp) { tab, position ->
            tab.text = when (position) {
                0 -> "수록곡"    // 첫 번째 탭 텍스트 (0번 인덱스)
                1 -> "상세정보"  // 두 번째 탭 텍스트 (1번 인덱스)
                else -> "영상"     // 세 번째 탭 텍스트 (2번 인덱스)
            }
        }.attach() // 반드시 attach()를 호출하여 동기화

        return binding.root
    }
}