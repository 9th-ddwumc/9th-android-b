package dduw.com.mobile.week5_gini

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dduw.com.mobile.week5_gini.album.AlbumFragment
import dduw.com.mobile.week5_gini.data.TodayMusicData
import dduw.com.mobile.week5_gini.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private lateinit var miniPlayerUpdater: MiniPlayerUpdater

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MiniPlayerUpdater) {
            miniPlayerUpdater = context
        } else {
            // 오류 처리
            throw RuntimeException("$context must implement MiniPlayerUpdater")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

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
            add(TodayMusicData(R.drawable.img_album_exp2, "LILAC", "아이유 (IU)" , "LILAC"))
            add(TodayMusicData(R.drawable.img_album_exp, "BUtter", "BTS", "Butter(Megan Thee Stallion Remix"))
            add(TodayMusicData(R.drawable.img_album_exp2, "LILAC", "아이유 (IU)" , "LILAC"))
        }

        val adapter = TodayMusicAdapter(todayMusicList)

        adapter.onAlbumItemClicked = { clickedItem ->
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm , AlbumFragment())
                .commitAllowingStateLoss()
        }

        adapter.onPlayButtonClicked = { item ->
            // MiniPlayerUpdater 인터페이스를 통해 Activity의 함수를 호출합니다.
            miniPlayerUpdater.updateMiniPlayer(item.firstSongTitle, item.singer)
        }

        binding.homeTodayMusicRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.homeTodayMusicRecyclerview.adapter = adapter
    }
}