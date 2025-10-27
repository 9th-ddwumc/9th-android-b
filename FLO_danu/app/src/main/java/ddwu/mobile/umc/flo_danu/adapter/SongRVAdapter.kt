package com.example.flo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.util.SparseBooleanArray
import ddwu.mobile.umc.flo_danu.R
import ddwu.mobile.umc.flo_danu.data.Song
import ddwu.mobile.umc.flo_danu.databinding.ItemSongBinding

class SongRVAdapter(
    private val context: Context,
    initial: List<Song>
) : RecyclerView.Adapter<SongRVAdapter.ViewHolder>() {

    companion object {
        private const val PAYLOAD_PLAY_TOGGLE = "PAYLOAD_PLAY_TOGGLE"
    }

    private val songs: MutableList<Song> = initial.toMutableList()
    // position -> isPlaying
    private val playingStates = SparseBooleanArray()

    init { setHasStableIds(true) }

    inner class ViewHolder(val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.itemAlbumSongTitleTv
        val singer: TextView = binding.itemAlbumSongSingerTv
        val number: TextView = binding.itemAlbumSongNumTv
        val play: ImageButton = binding.itemAlbumSongPlayIb
        val more: ImageButton = binding.itemAlbumSongMoreIb

        fun setPlayIcon(isPlaying: Boolean) {
            play.setImageResource(
                if (isPlaying) R.drawable.btn_miniplay_pause else R.drawable.btn_player_play
            )
        }

        @Suppress("DEPRECATION")
        fun bind(song: Song, position: Int) {
            title.text = song.title
            singer.text = song.singer
            number.text = String.format("%02d", position + 1)

            // 현재 상태에 맞춰 아이콘 표시
            setPlayIcon(playingStates.get(position, false))

            // play -> 상태 토글 + 콜백
            play.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val newState = !playingStates.get(pos, false)
                    playingStates.put(pos, newState)
                    // 부분 갱신으로 깜빡임 방지
                    notifyItemChanged(pos, PAYLOAD_PLAY_TOGGLE)
                    if (newState) {
                        mItemClickListener?.onPlay(pos)
                    }
                }
            }

            // […] More -> 삭제
            more.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val removed = songs.removeAt(pos)
                    // 상태도 정리 (간단히 초기화)
                    playingStates.clear()
                    notifyItemRemoved(pos)
                    notifyItemRangeChanged(pos, songs.size - pos)
                    mItemClickListener?.onRemoveSong(removed.id)
                }
            }
        }
    }

    interface MyItemClickListener {
        fun onRemoveSong(songId: Int)
        fun onPlay(position: Int)
        fun onMore(position: Int)
    }

    private var mItemClickListener: MyItemClickListener? = null
    fun setMyItemClickListener(listener: MyItemClickListener) { mItemClickListener = listener }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(songs[position], position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.any { it == PAYLOAD_PLAY_TOGGLE }) {
            holder.setPlayIcon(playingStates.get(position, false))
            return
        }
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun getItemCount(): Int = songs.size

    override fun getItemId(position: Int): Long {
        val s = songs[position]
        // If Room PK exists and is non-zero, use it. Otherwise, synthesize a stable key.
        return if (s.id != 0) {
            // Prefix to avoid accidental collisions with synthesized ids
            ("db|" + s.id).hashCode().toLong()
        } else {
            // For in-memory/dummy items that often all have id=0 → make a unique key per row
            ("mem|" + s.title + "|" + s.singer + "|" + position).hashCode().toLong()
        }
    }
}