package dduw.com.mobile.week6_gini

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MyMusicService : Service() {
    private val CHANNEL_ID = "ForegroundMusicService"
    private val NOTI_ID = 713

    private var mediaPlayer: MediaPlayer? = null
    private val binder = MusicBinder()

    //현재 재생 중인 노래 정보를 저장할 변수
    private var currentSongTitle: String = "Unknown Title"
    private var currentSongArtist: String = "Unknown Artist"

    //Activity에 Service 인스턴스를 전달
    inner class MusicBinder : Binder() {
        fun getService(): MyMusicService = this@MyMusicService
    }

    //서비스 시작 시 초기화
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        //Notification 활성화
        createNotificationChannel()

        val notification = createNotification() // ⭐ createNotification() 호출
        startForeground(NOTI_ID, notification)

        //Intent로 받은 정보들을 파싱
        val initialTitle = intent?.getStringExtra("songTitle") ?: "Unknown Title"
        val initialArtist = intent?.getStringExtra("songArtist") ?: "Unknown Artist"
        val isPlaying = intent?.getBooleanExtra("isPlaying", false) ?: false

        //MediaPlayer 및 변수 초기화 (임의로 노래를 연결)
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.aoa_miniskirt)
            currentSongTitle = initialTitle
            currentSongArtist = initialArtist
            if (isPlaying) {
                mediaPlayer?.start()
            }
        }

        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    //알람 채널 설정(CHANNEL_ID와 name은 자유롭게 설정!)
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Music Service Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    //알람으로 노래가 재생중임을 표시
    private fun createNotification(): Notification {
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("음악 재생 중")
            .setContentText("$currentSongTitle 이/가 재생 중입니다.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setOngoing(true)
            .setOnlyAlertOnce(true)

        return notificationBuilder.build()
    }

    //외부에서 서비스에 접근 가능한 함수들
    //음악 재생
    fun playMusic() {
        mediaPlayer?.start()
    }
    //음악 멈춤
    fun pauseMusic() {
        mediaPlayer?.pause()
    }
    //인자로 받은 위치로 곡의 재생 위치 이동
    fun seekTo(position: Int) {
        mediaPlayer?.seekTo(position)
    }
    //현재 재생중인 노래 정보 업데이트
    fun updateCurrentSongInfo(title: String, artist: String) {
        currentSongTitle = title
        currentSongArtist = artist
    }
    //현재 재생중인 노래의 길이 리턴
    fun getDuration(): Int {
        return mediaPlayer?.duration ?: 0
    }
    //현재 노래의 위치 리턴(SeekBar에 넣을 거)
    fun getCurrentPosition(): Int {
        return mediaPlayer?.currentPosition ?: 0
    }
    //재생 중임?
    fun isPlaying(): Boolean {
        return mediaPlayer?.isPlaying ?: false
    }

    override fun onDestroy() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }
}