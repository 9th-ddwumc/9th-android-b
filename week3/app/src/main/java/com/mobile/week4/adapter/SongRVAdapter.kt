package com.mobile.week4.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobile.week4.data.AlbumDto
import com.mobile.week4.databinding.ItemSongBinding

class SongRVAdapter(val context: Context, val result : List<AlbumDto>) : RecyclerView.Adapter<SongRVAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SongRVAdapter.ViewHolder {
        val binding: ItemSongBinding = ItemSongBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongRVAdapter.ViewHolder, position: Int) {
        holder.title.text = result[position].title
        holder.singer.text = result[position].singer

    }

    override fun getItemCount(): Int = result.size


    inner class ViewHolder(val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root){

        val coverImg : ImageView = binding.itemSongImgIv
        val title : TextView = binding.itemSongTitleTv
        val singer : TextView = binding.itemSongSingerTv
    }

    interface MyItemClickListener {
        fun onRemoveSong(songId: Int)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }
}