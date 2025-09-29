package com.moblie.umc_9th

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moblie.umc_9th.databinding.FragmentSonglistBinding

class SongListFragment : Fragment() {

    lateinit var binding: FragmentSonglistBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSonglistBinding.inflate(inflater, container, false)
        // "내 취향 MIX" 스위치 (스크린샷에 있는 부분)
        // TODO: 스위치 클릭 리스너 구현 (선택 사항)
        return binding.root
    }
}