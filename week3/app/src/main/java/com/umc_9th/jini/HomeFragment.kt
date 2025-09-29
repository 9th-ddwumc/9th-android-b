package com.umc_9th.jini

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.umc_9th.jini.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // 기존 앨범 이미지 클릭 리스너는 그대로 유지합니다.
        binding.homeAlbumImgIv1.setOnClickListener {
            (context as? MainActivity)?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_frm, AlbumFragment())
                ?.commitAllowingStateLoss()
        }

        return binding.root
    }

    // 뷰가 생성된 후에 UI 관련 초기화 작업을 진행합니다.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bannerAdapter = BannerVpAdapter(this)

        bannerAdapter.addFragment(BannerFragment(R.drawable.img_first_album_default))

        binding.homeBannerVp.adapter = bannerAdapter

        // 4. ViewPager2가 좌우로 스크롤되도록 방향을 설정합니다.
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }
}

