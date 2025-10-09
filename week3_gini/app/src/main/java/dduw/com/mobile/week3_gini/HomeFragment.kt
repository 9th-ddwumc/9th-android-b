package dduw.com.mobile.week3_gini

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dduw.com.mobile.week3_gini.album.AlbumFragment
import dduw.com.mobile.week3_gini.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.homeAlbumImgIv1.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm , AlbumFragment())
                .commitAllowingStateLoss()
        }

        //ViewPager2 참조
        val homeViewPager = binding.homePannelViewpager2

        //어댑터 연결
        val bannerAdapter = BannerVPAdapter(this)
        homeViewPager.adapter = bannerAdapter

        return binding.root
    }


}