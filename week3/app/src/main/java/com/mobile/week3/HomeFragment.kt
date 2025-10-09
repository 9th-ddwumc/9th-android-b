package com.mobile.week3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.mobile.week3.adapter.AlbumRVAdapter
import com.mobile.week3.data.AlbumDto
import com.mobile.week3.databinding.FragmentHomeBinding
import com.mobile.week3.adapter.BannerVPAdapter
import com.mobile.week3.adapter.PannelVPAdapter
import com.mobile.week3.view_model.SharedViewModel
import com.mobile.week3.viewpager.home.BannerFragment
import com.mobile.week3.viewpager.home.PannelFragment
import java.util.ArrayList

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var albumDatas = ArrayList<AlbumDto>()
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // 아래가 추가된 부분. 브랜치 실수..
        albumDatas.add(0, AlbumDto(1, "제목1", "가수1", R.drawable.img_album_exp))
        albumDatas.add(1, AlbumDto(2, "제목2", "가수2", R.drawable.img_album_exp2))
        albumDatas.add(2, AlbumDto(3, "제목3", "가수3", R.drawable.img_album_exp3))
        albumDatas.add(3, AlbumDto(4, "제목4", "가수4", R.drawable.img_album_exp4))
        albumDatas.add(4, AlbumDto(5, "제목5", "가수5", R.drawable.img_album_exp5))
        albumDatas.add(5, AlbumDto(6, "제목6", "가수6", R.drawable.img_album_exp6))
        val albumRVAdapter = AlbumRVAdapter(albumDatas)
        binding.homeTodayMusicAlbumRv.adapter = albumRVAdapter

        albumRVAdapter.setMyItemClickListener(object : AlbumRVAdapter.MyItemClickListener {
            override fun onItemClick(album: AlbumDto) {
                viewModel.setAlbum(album)

                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, AlbumFragment())
                    .commitAllowingStateLoss()
            }

            override fun onRemoveAlbum(position: Int) {}
        })



        val bannerAdapter = BannerVPAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        binding.homeBannerVp.adapter = bannerAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val pannelAdapter = PannelVPAdapter(this)
        pannelAdapter.addImg(PannelFragment(R.drawable.img_first_album_default))
        pannelAdapter.addImg(PannelFragment(R.drawable.img_album_exp2))
        binding.homePannelBackgroundVp.adapter = pannelAdapter
        TabLayoutMediator(
            binding.pannelIndicator,
            binding.homePannelBackgroundVp
        ) { _, _ -> }.attach()
        return binding.root
    }
}