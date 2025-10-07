package com.mobile.week3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.mobile.week3.adapter.AlbumVPAdapter
import com.mobile.week3.databinding.FragmentAlbumBinding
import com.mobile.week3.view_model.SharedViewModel

class AlbumFragment : Fragment() {
    private lateinit var binding: FragmentAlbumBinding
    private val viewModel: SharedViewModel by activityViewModels()
    private val information = arrayListOf("수록곡", "상세정보", "영상")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)

        binding.albumBackIv.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }

        val albumAdapter = AlbumVPAdapter(this)
        binding.albumContentVp.adapter = albumAdapter

        TabLayoutMediator(binding.albumContentTb, binding.albumContentVp) {
            tab, position ->
            tab.text = information[position]
        }.attach()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedAlbum.observe(viewLifecycleOwner) { song ->
            binding.albumMusicTitleTv.setText(song.title)
            binding.albumSingerNameTv.setText(song.singer)
            binding.albumAlbumIv.setImageResource(song.coverImg)
        }
    }
}