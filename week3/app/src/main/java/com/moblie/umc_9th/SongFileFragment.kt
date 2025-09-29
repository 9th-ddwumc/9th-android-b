package com.moblie.umc_9th

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moblie.umc_9th.databinding.FragmentSongfileBinding

class SongFileFragment : Fragment() {
    lateinit var binding: FragmentSongfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongfileBinding.inflate(inflater, container, false)
        // 여기에 음악파일 목록을 위한 UI 구현
        return binding.root
    }
}