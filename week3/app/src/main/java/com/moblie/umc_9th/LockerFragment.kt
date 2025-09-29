package com.moblie.umc_9th

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.moblie.umc_9th.databinding.FragmentLockerBinding

class LockerFragment : Fragment() {
    lateinit var binding: FragmentLockerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerBinding.inflate(inflater, container, false)
        // ViewPager2에 연결할 어댑터 생성
        val lockerAdapter = LockerViewAdapter(this)
        lockerAdapter.addFragment(SaveSongFragment()) // 저장한 곡 탭
        lockerAdapter.addFragment(SongFileFragment()) // 음악파일 탭
        lockerAdapter.addFragment(SaveAlbumFragment()) // 저장앨범 탭

        // ViewPager2에 어댑터 연결
        binding.lockerVp.adapter = lockerAdapter

        // TabLayout과 ViewPager2 연결
        TabLayoutMediator(binding.lockerTb, binding.lockerVp) { tab, position ->
            tab.text = when (position) {
                0 -> "저장한 곡"
                1 -> "음악파일"
                else -> "저장앨범"
            }
        }.attach()

        return binding.root
    }
}
