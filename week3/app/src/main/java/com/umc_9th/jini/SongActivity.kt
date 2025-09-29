package com.umc_9th.jini

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.umc_9th.jini.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {

    lateinit var binding : ActivitySongBinding
    private var isRepeating = false
    private var isShuffling = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("title") && intent.hasExtra("singer")){
            binding.songMusicTitleTv.text = intent.getStringExtra("title")
            binding.songSingerNameTv.text = intent.getStringExtra("singer")
        }



        binding.songRepeatIv.setOnClickListener {
            isRepeating = !isRepeating
            if (isRepeating) {
                binding.songRepeatIv.setImageResource(R.drawable.nugu_btn_repeat_inactive)
            } else {
                binding.songRepeatIv.setImageResource(R.drawable.nugu_btn_repeat_inactive)
            }
        }

        binding.songRandomIv.setOnClickListener {
            isShuffling = !isShuffling
            if (isShuffling) {
                binding.songRandomIv.setImageResource(R.drawable.nugu_btn_random_active)
            } else {
                binding.songRandomIv.setImageResource(R.drawable.nugu_btn_random_inactive)
            }
        }


        binding.songDownIb.setOnClickListener {
            finishWithResult()
        }
        binding.songMiniplayerIv.setOnClickListener {
            setPlayerStatus(false)
        }
        binding.songPauseIv.setOnClickListener {
            setPlayerStatus(true)
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishWithResult()
            }
        }
        this.onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun finishWithResult() {
        val returnIntent = Intent()
        val albumTitle = binding.songMusicTitleTv.text.toString()
        returnIntent.putExtra("album", albumTitle)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
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
}