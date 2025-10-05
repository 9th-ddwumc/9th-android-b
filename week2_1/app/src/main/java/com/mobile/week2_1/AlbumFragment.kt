package com.mobile.week2_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.mobile.week2_1.databinding.FragmentAlbumBinding
import com.mobile.week2_1.viewModel.SharedViewModel

class AlbumFragment : Fragment() {
    private lateinit var binding: FragmentAlbumBinding
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)

        binding.albumBackIv.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }
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