package com.umc_9th.jini

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc_9th.jini.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 부모 프래그먼트인 AlbumFragment에 접근
        val parentFragment = parentFragment as AlbumFragment

        // AlbumFragment의 TextView에서 앨범 제목과 가수 이름을 가져옴
        val albumTitle = parentFragment.binding.albumMusicTitleTv.text
        val albumSinger = parentFragment.binding.albumSingerNameTv.text

        // 가져온 정보로 현재 프래그먼트의 TextView를 설정
        binding.detailAlbumTitleTv.text = "이 앨범의 이름은 ${albumTitle}입니다."
        binding.detailAlbumSingerTv.text = "이 앨범의 작곡가는 ${albumSinger}입니다."
    }
}