package com.mobile.week4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.week4.data.AlbumDto
import com.mobile.week4.databinding.ItemAlbumBinding

class AlbumRVAdapter (private val albumList: ArrayList<AlbumDto>) : RecyclerView.Adapter<AlbumRVAdapter.ViewHolder>(){

    interface MyItemClickListener{
        fun onItemClick(album: AlbumDto)
        fun onRemoveAlbum(position: Int)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    // AlbumRVAdapter.kt
    interface MyItemPlayClickListener {
        fun onPlayClick(album: AlbumDto)
    }
    private lateinit var mItemPlayClickListener: MyItemPlayClickListener
    fun setMyItemPlayClickListener(listener: MyItemPlayClickListener) {
        mItemPlayClickListener = listener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AlbumRVAdapter.ViewHolder {
        val binding: ItemAlbumBinding = ItemAlbumBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    fun addItem(album: AlbumDto){
        albumList.add(album)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        albumList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: AlbumRVAdapter.ViewHolder, position: Int) {
        holder.bind(albumList[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(albumList[position]) }
    }

    override fun getItemCount(): Int = albumList.size

    inner class ViewHolder(val binding: ItemAlbumBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(album: AlbumDto){
            binding.itemAlbumTitleTv.text = album.title
            binding.itemAlbumSingerTv.text = album.singer
            binding.itemAlbumCoverImgIv.setImageResource(album.coverImg!!)

            binding.itemAlbumPlayImgIv.setOnClickListener {
                mItemPlayClickListener.onPlayClick(album)
            }
        }
    }
}