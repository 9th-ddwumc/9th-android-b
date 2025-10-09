package com.mobile.week4.viewpager.locker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobile.week4.databinding.FragmentMusicFileBinding

class MusicFileFragment : Fragment() {

    lateinit var binding: FragmentMusicFileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMusicFileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}