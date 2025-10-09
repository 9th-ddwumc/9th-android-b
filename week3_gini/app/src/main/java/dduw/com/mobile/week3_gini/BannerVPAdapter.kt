package dduw.com.mobile.week3_gini

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BannerVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    // 배너에 표시할 이미지 리소스 ID 목록
    private val bannerImages = listOf(
        R.drawable.img_first_album_default,
        R.drawable.img_album_exp,
        R.drawable.img_album_exp2 // 다른 이미지 리소스
    )

    override fun getItemCount(): Int = bannerImages.size // 페이지 수

    override fun createFragment(position: Int): Fragment {
        //각 위치에 맞는 이미지전달해 Fragment 생성
        return BannerFragment.newInstance(bannerImages[position])
    }
}