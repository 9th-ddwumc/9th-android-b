package dduw.com.mobile.week6_gini.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dduw.com.mobile.week6_gini.data.AlbumFragmentData
import dduw.com.mobile.week6_gini.databinding.ItemListSongBinding

class AlbumRecycleAdapter(private var albumList: MutableList<AlbumFragmentData>) :
                          RecyclerView.Adapter<AlbumRecycleAdapter.AlbumViewHolder>()  {

    inner class AlbumViewHolder(val binding: ItemListSongBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(album: AlbumFragmentData) {
            binding.txtSongNum.text = album.songNumber
            binding.txtIntemSong.text = album.song
            binding.txtItemSiger.text = album.singer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ItemListSongBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val nowAlbum = albumList[position]
        holder.bind(nowAlbum)
    }

    override fun getItemCount(): Int {
        return albumList.size
    }
}