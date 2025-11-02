package dduw.com.mobile.week4_gini.locker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dduw.com.mobile.week4_gini.data.LockerSavedSongData
import dduw.com.mobile.week4_gini.databinding.ItemSaveSongBinding

class LockerSavedSongRecycleAdapter(private var albumList: MutableList<LockerSavedSongData>, private val onVisitClicked: (position: Int) -> Unit) :
    RecyclerView.Adapter<LockerSavedSongRecycleAdapter.AlbumViewHolder>()  {

    inner class AlbumViewHolder(val binding: ItemSaveSongBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(album: LockerSavedSongData) {
            binding.savedSongImage.setImageResource(album.albumImage)
            binding.txtItemSavedSong.text = album.song
            binding.txtItemSavedSinger.text = album.singer
            binding.itemetcIcon.setOnClickListener{
                onVisitClicked(bindingAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ItemSaveSongBinding.inflate(
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

    fun deleteItem(position: Int) {
        if (position >= 0 && position < albumList.size) {
            albumList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, albumList.size);
        }
    }
}