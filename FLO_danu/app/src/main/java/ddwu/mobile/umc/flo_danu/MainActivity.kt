package ddwu.mobile.umc.flo_danu

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.LookFragment
import com.example.flo.SearchFragment
import ddwu.mobile.umc.flo_danu.data.Song
import ddwu.mobile.umc.flo_danu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var currentSong: Song? = null

    val lilac: Song = Song("LILAC", "아이유 (IU)", R.drawable.img_album_exp2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainPlayingTitleTv.text = lilac.title
        binding.mainPlayingSingerTv.text = lilac.singer

        binding.mainPlayerCl.setOnClickListener {
            val songForIntent: Song = currentSong ?: Song(
                title = lilac.title,
                singer = lilac.singer,
                playTime = 180,      // 기본 3:00
                second = 0,
                isPlaying = false,
                music = "",
                coverImg = lilac.coverImg,
                isLike = false
            )
            val intent = Intent(this, SongActivity::class.java).apply {
                putExtra("song", songForIntent)
            }
            startActivity(intent)
        }

        initBottomNavigation()
    }

    fun updateMiniPlayer(song: Song) {
        currentSong = song
        binding.mainPlayingTitleTv.text = song.title
        binding.mainPlayingSingerTv.text = song.singer
        binding.mainPlayerCl.visibility = View.VISIBLE
    }

    private fun initBottomNavigation() {
        val homeFragment = HomeFragment().apply {
            arguments = Bundle().apply {
                putSerializable("Lilac", lilac)
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, homeFragment)
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.homeFragment -> {
                    val homeFragment = HomeFragment().apply {
                        arguments = Bundle().apply {
                            putSerializable("Lilac", lilac)
                        }
                    }
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, homeFragment)
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.lookFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LookFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.searchFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, SearchFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.lockerFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LockerFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}