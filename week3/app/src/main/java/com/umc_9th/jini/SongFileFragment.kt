package com.umc_9th.jini

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc_9th.jini.databinding.FragmentSongFileBinding // ViewBinding import

class SongFileFragment : Fragment() {
    lateinit var binding: FragmentSongFileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongFileBinding.inflate(inflater, container, false)
        return binding.root
    }
}