package com.umc_9th.jini

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.umc_9th.jini.databinding.FragmentLockerBinding

class LockerFragment : Fragment() {

    lateinit var binding: FragmentLockerBinding
    private val tabTitleArray = arrayOf("저장한 곡", "음악파일", "저장앨범")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. ViewPager2에 어댑터를 연결합니다.
        val lockerAdapter = LockerViewAdapter(this)
        binding.lockerContentVp.adapter = lockerAdapter

        // 2. TabLayout과 ViewPager2를 연결하고, 탭의 제목을 설정합니다.
        TabLayoutMediator(binding.lockerContentTb, binding.lockerContentVp) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()
    }
}

//package com.umc_9th.jini
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import com.google.android.material.tabs.TabLayoutMediator // TabLayoutMediator import 추가
//import com.umc_9th.jini.databinding.FragmentLockerBinding
//
//class LockerFragment : Fragment() {
//
//    lateinit var binding: FragmentLockerBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentLockerBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // ViewPager2에 어댑터 연결
//        val lockerAdapter = LockerViewAdapter(this) // <- 올바른 이름으로 수정
//        binding.vpLockerLockerFragment.adapter = lockerAdapter
//
//        // TabLayout과 ViewPager2 연결 및 탭 제목 설정
//        TabLayoutMediator(binding.tblStorelayLockerFragment, binding.vpLockerLockerFragment) { tab, position ->
//            tab.text = when (position) {
//                0 -> "저장한 곡"
//                1 -> "음악파일"
//                2 -> "저장앨범"
//                else -> ""
//            }
//        }.attach()
//    }
//}