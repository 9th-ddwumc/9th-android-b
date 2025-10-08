package com.mobile.week3.viewpager.locker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.week3.R
import com.mobile.week3.databinding.FragmentSavedAlbumBinding

class SavedAlbumFragment : Fragment() {

    lateinit var binding: FragmentSavedAlbumBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }
}