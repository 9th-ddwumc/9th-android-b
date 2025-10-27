package dduw.com.mobile.week5_gini

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dduw.com.mobile.week5_gini.data.Song
import dduw.com.mobile.week5_gini.databinding.ActivityMainBinding
import dduw.com.mobile.week5_gini.locker.LockerFragment
import dduw.com.mobile.week5_gini.locker.LookFragment

class MainActivity : AppCompatActivity(), MiniPlayerUpdater {

    //val parentFragmentManager: Any
    lateinit var binding: ActivityMainBinding

    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val title = result.data?.getStringExtra("title")
            Toast.makeText(this, "Comeback form SongActivity 제목: $title", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // 1. 스플래시 설치 (가장 먼저)
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 2. 바인딩 및 setContentView 호출
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 👆 이제 R.layout.activity_main 뷰가 로드되었습니다.

        // 3. 스플래시 유지 조건 설정
        val startTime = System.currentTimeMillis()
        splashScreen.setKeepOnScreenCondition {
            System.currentTimeMillis() - startTime < 2000 // 2초 동안 유지
        }

        // 4. 나머지 초기화 로직
        initBottomNavigation()

        val song = Song(
            binding.mainMiniplayerTitleTv.text.toString(),
            binding.mainMiniplayerSingerTv.text.toString()
        )

        binding.mainPlayerCl.setOnClickListener {
            val intent = Intent(this, SongActivity::class.java)
        }

        binding.mainPlayerCl.setOnClickListener {
            val intent = Intent(this,SongActivity::class.java)
            intent.putExtra("title", song.title)
            intent.putExtra("singer",song.singer)
            resultLauncher.launch(intent)
        }

    }
    override fun updateMiniPlayer(title: String, singer: String) {
        binding.mainMiniplayerTitleTv.text = title
        binding.mainMiniplayerSingerTv.text = singer
    }

    private fun initBottomNavigation(){

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener{ item ->
            when (item.itemId) {

                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
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

interface MiniPlayerUpdater {
    fun updateMiniPlayer(title: String, singer: String)
}
