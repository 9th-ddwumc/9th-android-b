package dduw.com.mobile.week3_gini

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dduw.com.mobile.week3_gini.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {
    lateinit var binding : ActivitySongBinding

    var isRepeating = false
    var isRandomPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        }

        binding.songPauseIv.setOnClickListener {
            setPlayerStatus(true)
        }

        binding.songRepeatIv.setOnClickListener{
            toggleRepeatStatus()
        }

        binding.songRandomIv.setOnClickListener{
            toggleRandomStatus()
        }

    }

    fun setPlayerStatus (isPlaying : Boolean){
        if(isPlaying){
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
        } else {
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
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
}