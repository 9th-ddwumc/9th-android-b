package com.umc_9th.jini

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc_9th.jini.databinding.FragmentSaveSongBinding // ViewBinding import

class SaveSongFragment : Fragment() {
    lateinit var binding: FragmentSaveSongBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveSongBinding.inflate(inflater, container, false)
        return binding.root
    }
}