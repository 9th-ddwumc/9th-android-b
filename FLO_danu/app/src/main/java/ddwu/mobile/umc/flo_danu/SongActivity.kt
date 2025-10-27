package ddwu.mobile.umc.flo_danu

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import ddwu.mobile.umc.flo_danu.data.Album
import ddwu.mobile.umc.flo_danu.data.Song
import ddwu.mobile.umc.flo_danu.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {

    lateinit var binding: ActivitySongBinding
    private lateinit var song: Song
    private var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.songProgressSb.max = 1000

        initFromIntent()
        setPlayer(song)
        startTimer()

        binding.songDownIb.setOnClickListener { finish() }

        binding.songPlayIb.setOnClickListener {
            binding.songPlayIb.visibility = View.GONE
            binding.songPauseIb.visibility = View.VISIBLE
            song.isPlaying = true
            if (timer == null) startTimer() else timer?.isPlaying = true
        }

        binding.songPauseIb.setOnClickListener {
            binding.songPauseIb.visibility = View.GONE
            binding.songPlayIb.visibility = View.VISIBLE
            song.isPlaying = false
            timer?.isPlaying = false
        }

        binding.songPreviousIb.setOnClickListener { resetCurrentSong() }
        binding.songNextIb.setOnClickListener { resetCurrentSong() }

        binding.songRepeatIb.setOnClickListener {
            binding.songRepeatIb.visibility = View.GONE
            binding.songRepeatActIb.visibility = View.VISIBLE
        }
        binding.songRepeatActIb.setOnClickListener {
            binding.songRepeatActIb.visibility = View.GONE
            binding.songRepeatIb.visibility = View.VISIBLE
        }

        binding.songRandomIb.setOnClickListener {
            binding.songRandomIb.visibility = View.GONE
            binding.songRandomActIb.visibility = View.VISIBLE
        }
        binding.songRandomActIb.setOnClickListener {
            binding.songRandomActIb.visibility = View.GONE
            binding.songRandomIb.visibility = View.VISIBLE
        }

        binding.songProgressSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            private var userTouch = false

            override fun onStartTrackingTouch(sb: SeekBar?) { userTouch = true }

            override fun onStopTrackingTouch(sb: SeekBar?) {
                userTouch = false
                val newSec = if (song.playTime > 0)
                    ((binding.songProgressSb.progress / 1000f) * song.playTime).toInt().coerceIn(0, song.playTime)
                else 0
                seekToSec(newSec)
            }

            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                if (!fromUser) return
                val previewSec = if (song.playTime > 0)
                    ((progress / 1000f) * song.playTime).toInt().coerceIn(0, song.playTime)
                else 0
                binding.songStartTimeTv.text = String.format("%02d:%02d", previewSec / 60, previewSec % 60)
            }
        })
    }

    private fun initFromIntent() {
        val songExtra = intent.getSerializableExtra("song") as? Song
        if (songExtra != null) {
            song = songExtra
            return
        }

        val albumExtra = intent.getSerializableExtra("Lilac") as? Album
        if (albumExtra != null) {
            song = Song(
                title = albumExtra.title,
                singer = albumExtra.singer,
                second = 0,
                playTime = 180,
                isPlaying = false,
                music = "",
                coverImg = albumExtra.coverImg,
                isLike = false
            )
            return
        }

        song = Song(title = "", singer = "", second = 0, playTime = 0)
    }

    private fun setPlayer(song: Song) {
        binding.songTitleTv.text = song.title
        binding.songSingerTv.text = song.singer
        binding.songStartTimeTv.text =
            String.format("%02d:%02d", song.second / 60, song.second % 60)
        binding.songEndTimeTv.text =
            String.format("%02d:%02d", song.playTime / 60, song.playTime % 60)

        // 0..1000 스케일로 환산
        val progress = if (song.playTime > 0) {
            (song.second * 1000 / song.playTime).coerceIn(0, 1000)
        } else 0
        binding.songProgressSb.progress = progress
        song.coverImg?.let { binding.songAlbumIv.setImageResource(it) }

        setPlayerStatus(song.isPlaying)
    }

    private fun setPlayerStatus(isPlaying: Boolean) {
        song.isPlaying = isPlaying
        timer?.isPlaying = isPlaying

        if (song.isPlaying) {
            binding.songPlayIb.visibility = View.GONE
            binding.songPauseIb.visibility = View.VISIBLE
        } else {
            binding.songPlayIb.visibility = View.VISIBLE
            binding.songPauseIb.visibility = View.GONE
        }
    }

    private fun resetCurrentSong() {
        stopTimer()
        song.second = 0
        song.isPlaying = false
        setPlayer(song) // 뷰 초기화
        startTimer()    // 타이머 재시작 (일시정지 상태)
    }

    private fun startTimer() {
        stopTimer()
        timer = Timer(song.playTime, song.isPlaying, ::onTick, ::onSongEnded).also { it.start() }
    }

    private fun stopTimer() {
        val t = timer ?: return
        t.requestStop()
        t.interrupt()
        timer = null
    }

    private fun onTick(msAccum: Int, playTime: Int) {
        val curSec = (msAccum / 1000).coerceAtMost(playTime)
        val progress = if (playTime > 0)
            ((msAccum.toFloat() / (playTime * 1000f)) * 1000).toInt().coerceIn(0, 1000)
        else 0
        binding.songProgressSb.progress = progress
        binding.songStartTimeTv.text = String.format("%02d:%02d", curSec / 60, curSec % 60)
        song.second = curSec
    }

    private fun seekToSec(targetSec: Int) {
        song.second = targetSec

        val newProgress = if (song.playTime > 0)
            ((targetSec / song.playTime.toFloat()) * 1000f).toInt().coerceIn(0, 1000)
        else 0
        binding.songProgressSb.progress = newProgress
        binding.songStartTimeTv.text = String.format("%02d:%02d", targetSec / 60, targetSec % 60)

        val playingBefore = song.isPlaying
        stopTimer()
        timer = Timer(song.playTime, playingBefore, ::onTick, ::onSongEnded).also { it.start() }
    }


    private fun onSongEnded() {
        setPlayerStatus(false)
    }

    inner class Timer(
        private val playTime: Int,
        @Volatile var isPlaying: Boolean = true,
        private val tick: (msAccum: Int, playTime: Int) -> Unit,
        private val ended: () -> Unit
    ) : Thread() {
        @Volatile private var running = true
        private var msAccum: Int = song.second * 1000

        fun requestStop() { running = false }

        override fun run() {
            while (running && msAccum / 1000 < playTime) {
                try {
                    sleep(50)
                } catch (_: InterruptedException) {
                    return
                }

                if (!running) return
                if (!isPlaying) continue

                msAccum += 50
                val curSec = (msAccum / 1000)

                runOnUiThread { tick(msAccum, playTime) }

                if (curSec >= playTime) {
                    isPlaying = false
                    runOnUiThread { ended() }
                    return
                }
            }
        }
    }

    override fun onDestroy() {
        stopTimer()
        super.onDestroy()
    }
}