package com.mobile.week3.viewpager.locker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.week3.R
import com.mobile.week3.databinding.FragmentSavedSongBinding

class SavedSongFragment : Fragment() {

    lateinit var binding: FragmentSavedSongBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedSongBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}