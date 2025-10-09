package com.mobile.week4

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.mobile.week4.data.AlbumDto
import com.mobile.week4.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {
    lateinit var binding: ActivitySongBinding
    val songs = arrayListOf<AlbumDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClickListener()
    }

    private fun initClickListener() {
        binding.songDownIb.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("result_song", binding.songMusicTitleTv.text)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        binding.songMiniplayerIv.setOnClickListener {
            // 나중에 함수로 바꾸기
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
        }

        binding.songPauseIv.setOnClickListener {
            // 나중에 함수로 바꾸기
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
        }

        binding.songNextIv.setOnClickListener {

        }

        binding.songPreviousIv.setOnClickListener {

        }
    }
}