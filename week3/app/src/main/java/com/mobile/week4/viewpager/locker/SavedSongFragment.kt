package com.mobile.week4.viewpager.locker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.week4.adapter.AlbumLockerRVAdapter
import com.mobile.week4.adapter.AlbumRVAdapter
import com.mobile.week4.data.AlbumDto
import com.mobile.week4.databinding.FragmentSavedSongBinding

class SavedSongFragment : Fragment() {

    lateinit var binding: FragmentSavedSongBinding
    private val savedSongs = ArrayList<AlbumDto>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedSongBinding.inflate(layoutInflater, container, false)

        val adapter = AlbumLockerRVAdapter()
        binding.lockerSavedSongRecyclerView.adapter = adapter

        adapter.setMyItemClickListener(object : AlbumLockerRVAdapter.MyItemClickListener {
            override fun onRemoveSong(songId: Int) {
                // 여기 해줘.,
            }
        })

        return binding.root
    }
}