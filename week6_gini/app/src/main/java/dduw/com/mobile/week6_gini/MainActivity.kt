package dduw.com.mobile.week6_gini

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import dduw.com.mobile.week6_gini.data.Song
import dduw.com.mobile.week6_gini.databinding.ActivityMainBinding
import dduw.com.mobile.week6_gini.locker.LockerFragment
import dduw.com.mobile.week6_gini.locker.LookFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), MiniPlayerUpdater {

    //val parentFragmentManager: Any
    lateinit var binding: ActivityMainBinding

    private var musicService: MyMusicService? = null
    private var isBound = false
    private var updateJob: Job? = null
    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val title = result.data?.getStringExtra("title")
            Toast.makeText(this, "Comeback form SongActivity 제목: $title", Toast.LENGTH_SHORT).show()
        }
    }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyMusicService.MusicBinder
            musicService = binder.getService()
            isBound = true
            updateUI()
            updateSeekbar()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val startTime = System.currentTimeMillis()
        splashScreen.setKeepOnScreenCondition {
            System.currentTimeMillis() - startTime < 2000 // 2초 동안 유지
        }

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

        binding.mainMiniplayerBtn.setOnClickListener {
            setPlayerStatus(false)
            musicService?.playMusic()
            updateUI()
            checkPlay()
        }

        binding.mainPauseBtn.setOnClickListener {
            setPlayerStatus(true)
            musicService?.pauseMusic()
            updateUI()
            checkPlay()
        }

        binding.btnMiniplayerPrevious.setOnClickListener {
            musicService?.seekTo(0)
            musicService?.playMusic()
            updateUI()
            checkPlay()
        }

        binding.btnMiniplayerNext.setOnClickListener {
            musicService?.seekTo(0)
            musicService?.playMusic()
            updateUI()
            checkPlay()
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

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, MyMusicService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()

        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }

    private fun checkPlay() {
        val isPlaying = musicService?.isPlaying() == true
        setPlayerStatus(isPlaying)

        if (isPlaying) {
            updateSeekbar() // 재생 중이면 SeekBar 업데이트 시작
        } else {
            updateJob?.cancel() // 멈춤이면 SeekBar 업데이트 중지
        }
    }
    fun setPlayerStatus (isPlaying : Boolean){
        if(isPlaying){
            binding.mainMiniplayerBtn.visibility = View.GONE
            binding.mainPauseBtn.visibility = View.VISIBLE
        } else {
            binding.mainMiniplayerBtn.visibility = View.VISIBLE
            binding.mainPauseBtn.visibility = View.GONE
        }
    }
    private fun updateSeekbar() {
        updateJob?.cancel()
        updateJob = lifecycleScope.launch(Dispatchers.Main) {
            while (isBound && musicService?.isPlaying() == true) {
                delay(100)
                val currentPosition = musicService!!.getCurrentPosition()
                binding.songProgressbarView.progress = currentPosition
            }
        }
    }
    private fun updateUI() {
        val duration = musicService?.getDuration() ?: 0
        binding.songProgressbarView.max = duration

        val isPlaying = musicService?.isPlaying() == true
        setPlayerStatus(isPlaying)
    }
}

interface MiniPlayerUpdater {
    fun updateMiniPlayer(title: String, singer: String)
}

