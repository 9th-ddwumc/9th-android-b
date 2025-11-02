package dduw.com.mobile.week6_gini

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dduw.com.mobile.week6_gini.album.AlbumFragment
import dduw.com.mobile.week6_gini.data.TodayMusicData
import dduw.com.mobile.week6_gini.databinding.FragmentHomeBinding
import me.relex.circleindicator.CircleIndicator3
import java.util.Timer
import kotlin.concurrent.timer

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private lateinit var miniPlayerUpdater: MiniPlayerUpdater

    private val handler = Handler(Looper.getMainLooper())
    private var timerTask: Timer? = null

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
        val indicator: CircleIndicator3 = binding.indicator

        //어댑터 연결
        val bannerAdapter = BannerVPAdapter(this)
        homeViewPager.adapter = bannerAdapter

        //인디케이터 연결
        indicator.setViewPager(homeViewPager)

        // 자동슬라이드 함수
        startAutoSlide(bannerAdapter)

        return binding.root
    }


    private fun startAutoSlide(adpater : BannerVPAdapter) {
        val delay = 3000L // 3초 후에 시작
        val period = 3000L // 3초 간격

        timerTask = timer(period = period) {
            handler.post {
                // ViewPager2 ID를 homePannelViewpager2로 통일하여 사용
                val viewPager = binding.homePannelViewpager2

                // 다음 페이지 인덱스 계산
                val nextItem = viewPager.currentItem + 1

                if (nextItem < adpater.itemCount) {
                    viewPager.currentItem = nextItem
                } else {
                    viewPager.currentItem = 0 // 마지막 페이지에서 첫 페이지로 순환
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        // 타이머의 모든 예약된 작업을 중지+타이머 종료
        timerTask?.cancel()
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