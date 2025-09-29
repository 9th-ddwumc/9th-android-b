package com.moblie.umc_9th

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moblie.umc_9th.databinding.FragmentSavealbumBinding

class SaveAlbumFragment: Fragment() {
    lateinit var binding: FragmentSavealbumBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavealbumBinding.inflate(inflater, container, false)
        // 여기에 저장앨범 목록을 위한 UI 구현
        return binding.root
    }
}