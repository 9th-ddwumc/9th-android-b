package com.moblie.umc_9th

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moblie.umc_9th.databinding.FragmentSavesongBinding

class SaveSongFragment: Fragment() {
    lateinit var binding: FragmentSavesongBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavesongBinding.inflate(inflater, container, false)

        // (이전 답변에서 제공했던 RecyclerView와 Adapter 관련 코드 추가)
        // ...

        return binding.root
    }
}