package com.mobile.week2_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.mobile.week2_1.data.SongDto
import com.mobile.week2_1.databinding.FragmentHomeBinding
import com.mobile.week2_1.viewModel.SharedViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.homeAlbumImgIv1.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, AlbumFragment())
                .commitAllowingStateLoss()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 임시 초기 이미지
        val song = SongDto("임시 DTO", "냥냥", R.drawable.img_album_exp5)
        binding.homeAlbumTitleTv1.setText(song.title)
        binding.homeAlbumSingerTv1.setText(song.singer)
        binding.homeAlbumImgIv1.setImageResource(song.coverImg)
        viewModel.setAlbum(song)
    }
}