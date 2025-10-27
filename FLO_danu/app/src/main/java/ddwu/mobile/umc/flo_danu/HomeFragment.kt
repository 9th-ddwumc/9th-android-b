package ddwu.mobile.umc.flo_danu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import ddwu.mobile.umc.flo_danu.adapter.AlbumRVAdapter
import ddwu.mobile.umc.flo_danu.adapter.BannerVPAdapter
import ddwu.mobile.umc.flo_danu.data.Album
import ddwu.mobile.umc.flo_danu.data.Song
import ddwu.mobile.umc.flo_danu.databinding.FragmentHomeBinding
import ddwu.mobile.umc.flo_danu.vp.home.BannerFragment

class HomeFragment: Fragment() {
    lateinit var binding: FragmentHomeBinding
    private var albumDatas = ArrayList<Album>()


    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val lilac = arguments?.getSerializable("Lilac") as? Album


        albumDatas.apply {
            add(Album("Butter", "방탄소년단 (BTS)", R.drawable.img_album_exp))
            add(Album("Lilac", "아이유 (IU)", R.drawable.img_album_exp2))
            add(Album("Next Level", "에스파 (AESPA)", R.drawable.img_album_exp3))
            add(Album("Boy with Luv", "방탄소년단 (BTS)", R.drawable.img_album_exp4))
            add(Album("BBoom BBoom", "모모랜드 (MOMOLAND)", R.drawable.img_album_exp5))
            add(Album("Weekend", "태연 (Tae Yeon)", R.drawable.img_album_exp6))
        }

        val albumRVAdapter = AlbumRVAdapter(
            albumList = albumDatas,
            onItemClick = { clickedAlbum ->
                val albumFragment = AlbumFragment().apply {
                    arguments = Bundle().apply { putSerializable("Lilac", clickedAlbum) }
                }
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, albumFragment)
                    .addToBackStack(null)
                    .commit()
            },
            onPlayClick = { album ->
                val first = album.songs?.firstOrNull()

                val song = Song(
                    title = first?.title ?: album.title,
                    singer = first?.singer ?: album.singer,
                    second = first?.second ?: 0,
                    playTime = first?.playTime ?: 180, // 기본 3:00
                    isPlaying = false,
                    music = first?.music ?: "",
                    coverImg = album.coverImg,
                    isLike = false
                )

                (activity as? MainActivity)?.updateMiniPlayer(song)
            }
        )
        binding.homeTodayMusicAlbumRv.adapter = albumRVAdapter
        binding.homeTodayMusicAlbumRv.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        binding.homeTodayMusicAlbum1Layout.setOnClickListener {
            val albumFragment = AlbumFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("Lilac", lilac)
                }
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, albumFragment)
                .addToBackStack(null)
                .commit()
        }

        val bannerAdapter = BannerVPAdapter(this)
        val indicator = binding.homeBannerIndicator

        bannerAdapter.addFragment(BannerFragment(R.drawable.img_first_album_default))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_first_album_default))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_first_album_default))
        binding.homeBannerVp.adapter = bannerAdapter
        indicator.setViewPager(binding.homeBannerVp)

        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        return binding.root
    }
}