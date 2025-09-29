package com.moblie.umc_9th

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.moblie.umc_9th.databinding.FragmentHomeBinding
import kotlin.text.replace

class HomeFragment : Fragment() {
    lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        //어댑터 연결
        val bannerAdapter = BannerVPAdpter(this)

        bannerAdapter.addFragment(BannerFragment(R.drawable.img_first_album_default))
//        bannerAdapter.addFragment(BannerFragment(R.drawable.img_album_exp2))
//        bannerAdapter.addFragment(BannerFragment(R.drawable.img_album_exp3))
//        bannerAdapter.addFragment(BannerFragment(R.drawable.img_album_exp4))
//        bannerAdapter.addFragment(BannerFragment(R.drawable.img_album_exp5))
//        bannerAdapter.addFragment(BannerFragment(R.drawable.img_album_exp6))

        binding.homePannelBackgroundVp.adapter = bannerAdapter
        binding.homePannelBackgroundVp.orientation = androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
        return binding.root
    }
}