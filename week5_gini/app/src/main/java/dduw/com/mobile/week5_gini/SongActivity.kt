package dduw.com.mobile.week5_gini

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dduw.com.mobile.week5_gini.databinding.ActivitySongBinding
import kotlinx.coroutines.Job
import androidx.core.content.ContextCompat
import android.content.Context
import dduw.com.mobile.week5_gini.data.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class SongActivity : AppCompatActivity() {
    lateinit var binding : ActivitySongBinding

    var isRepeating = false
    var isRandomPlaying = false

    private var musicService: MyMusicService? = null
    private var isBound = false
    private var updateJob: Job? = null

    private var currentSong: Song? = null

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
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //서비스 시작 및 바인딩
        currentSong = intent.getParcelableExtra("songData") as? Song

        val musicServiceIntent = Intent(this, MyMusicService::class.java).apply {
            putExtra("songTitle", currentSong?.title ?: "Unknown")
            putExtra("songArtist", currentSong?.singer ?: "Unknown")
            putExtra("isPlaying", true)
            putExtra("musicFileResId", currentSong?.musicFileResId ?: 0)
        }
        ContextCompat.startForegroundService(this, musicServiceIntent)
        bindService(musicServiceIntent, connection, Context.BIND_AUTO_CREATE)

        if(intent.hasExtra("title") && intent.hasExtra("singer")){
            binding.songMusicTitleTv.text = intent.getStringExtra("title")
            binding.songSingerNameTv.text = intent.getStringExtra("singer")
        }

        binding.songDownIb.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("title", binding.songMusicTitleTv.text.toString())
            setResult(RESULT_OK, returnIntent)
            finish()
        }

        binding.songMiniplayerIv.setOnClickListener {
            setPlayerStatus(false)
            musicService?.playMusic()
            updateUI()
            checkPlay()
        }

        binding.songPauseIv.setOnClickListener {
            setPlayerStatus(true)
            musicService?.pauseMusic()
            updateUI()
            checkPlay()
        }

        binding.songPreviousIv.setOnClickListener {
            musicService?.seekTo(0)
            musicService?.playMusic()
            updateUI()
            checkPlay()
        }

        binding.songNextIv.setOnClickListener {
            musicService?.seekTo(0)
            musicService?.playMusic()
            updateUI()
            checkPlay()
        }


        binding.songRepeatIv.setOnClickListener{
            toggleRepeatStatus()
        }

        binding.songRandomIv.setOnClickListener{
            toggleRandomStatus()
        }

        //SeekBar 터치 시 MediaPlayer에 영 song_progressbar_backgroud_view
        binding.songProgressbarView.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    musicService?.seekTo(progress)
                    binding.songStartTimeTv.text = milliTotime(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    fun setPlayerStatus (isPlaying : Boolean){
        if(isPlaying){
            // [수정] 재생 중일 때: 멈춤 아이콘을 보여주고, 재생 아이콘을 숨긴다.
            binding.songMiniplayerIv.visibility = View.GONE  // 재생 버튼 숨김
            binding.songPauseIv.visibility = View.VISIBLE   // 멈춤 버튼 보임 (사용자가 누를 버튼)
        } else {
            // [수정] 멈춤 중일 때: 재생 아이콘을 보여주고, 멈춤 아이콘을 숨긴다.
            binding.songMiniplayerIv.visibility = View.VISIBLE // 재생 버튼 보임 (사용자가 누를 버튼)
            binding.songPauseIv.visibility = View.GONE    // 멈춤 버튼 숨김
        }
    }

    fun toggleRepeatStatus() {
        isRepeating = !isRepeating
        if (isRepeating) {
            binding.songRepeatIv.setImageResource(R.drawable.nugu_btn_song_repeat_active)
        } else {
            binding.songRepeatIv.setImageResource(R.drawable.nugu_btn_repeat_inactive)
        }
    }

    fun toggleRandomStatus() {
        isRandomPlaying = !isRandomPlaying
        if (isRandomPlaying) {
            binding.songRandomIv.setImageResource(R.drawable.nugu_btn_random_active)
        } else {
            binding.songRandomIv.setImageResource(R.drawable.nugu_btn_random_inactive)
        }
    }

    //코루틴을 이용해, 음악 진행도를 SeekBar에 반영
    private fun updateSeekbar() {
        updateJob?.cancel()
        updateJob = lifecycleScope.launch(Dispatchers.Main) {
            while (isBound && musicService?.isPlaying() == true) {
                delay(100)
                val currentPosition = musicService!!.getCurrentPosition()
                binding.songProgressbarView.progress = currentPosition
                binding.songStartTimeTv.text = milliTotime(currentPosition)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        // 메모리 누수 방지: Activity가 화면에서 사라질 때 바인딩 해제
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
        updateJob?.cancel() // 코루틴 작업 취소
    }
    // 서비스 연결 시 호출되어 UI 상태를 동기화
    private fun updateUI() {
        val duration = musicService?.getDuration() ?: 0
        binding.songProgressbarView.max = duration

        binding.songEndTimeTv.text = milliTotime(duration)

        val isPlaying = musicService?.isPlaying() == true
        setPlayerStatus(isPlaying)
    }

    // 재생/멈춤 버튼 클릭 시 현재 상태를 토글
    private fun checkPlay() {
        val isPlaying = musicService?.isPlaying() == true
        setPlayerStatus(isPlaying)

        if (isPlaying) {
            updateSeekbar() // 재생 중이면 SeekBar 업데이트 시작
        } else {
            updateJob?.cancel() // 멈춤이면 SeekBar 업데이트 중지
        }
    }

    // 밀리초를 '분:초' 문자열로 변환하는 함수 (예시)
    private fun milliTotime(milliseconds: Int): String {
        val totalSeconds = milliseconds / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format("%02d:%02d", minutes, seconds)
    }
}

