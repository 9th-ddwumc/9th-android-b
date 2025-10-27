package dduw.com.mobile.week4_gini

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dduw.com.mobile.week4_gini.data.TodayMusicData
import dduw.com.mobile.week4_gini.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

//        binding.itemAlbumImg.setOnClickListener {
//            (context as MainActivity).supportFragmentManager.beginTransaction()
//                .replace(R.id.main_frm , AlbumFragment())
//                .commitAllowingStateLoss()
//        }

        //ViewPager2 참조
        val homeViewPager = binding.homePannelViewpager2

        //어댑터 연결
        val bannerAdapter = BannerVPAdapter(this)
        homeViewPager.adapter = bannerAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val todayMusicList = mutableListOf<TodayMusicData>().apply {
            add(TodayMusicData(R.drawable.img_album_exp2, "LILAC", "아이유 (IU)"))
            add(TodayMusicData(R.drawable.img_album_exp2, "Flu", "아이유 (IU)"))
            add(TodayMusicData(R.drawable.img_album_exp2, "Coin", "아이유 (IU)"))
        }

        val adapter = TodayMusicAdapter(todayMusicList)

        binding.homeTodayMusicRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.homeTodayMusicRecyclerview.adapter = adapter
    }
}