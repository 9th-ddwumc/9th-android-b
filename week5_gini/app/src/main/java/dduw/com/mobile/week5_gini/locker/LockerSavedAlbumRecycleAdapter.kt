package dduw.com.mobile.week5_gini.locker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dduw.com.mobile.week5_gini.R
import dduw.com.mobile.week5_gini.data.LockerSavedAlbumData
import dduw.com.mobile.week5_gini.databinding.ItemSaveAlbumBinding

class LockerSavedAlbumRecycleAdapter(private var albumList: MutableList<LockerSavedAlbumData>,
                                     private val onVisitClicked: (position: Int) -> Unit) :
    RecyclerView.Adapter<LockerSavedAlbumRecycleAdapter.AlbumViewHolder>(){

    inner class AlbumViewHolder(val binding: ItemSaveAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(album: LockerSavedAlbumData) {
            binding.savedAlbumImage.setImageResource(album.image)
            binding.txtItemSavedAlbum.text = album.album
            binding.txtItemSavedAlbumSinger.text = album.singer
            binding.txtItemSavedAlbumInfo.text = album.albumInfo
            binding.itemplayIcon.setOnClickListener {
                if (binding.itemplayIcon.tag == "is_playing") {
                    binding.itemplayIcon.setImageResource(R.drawable.btn_miniplayer_play)
                    binding.itemplayIcon.tag = "is_paused"
                } else {
                    binding.itemplayIcon.setImageResource(R.drawable.btn_miniplay_pause)
                    binding.itemplayIcon.tag = "is_playing"
                }
            }
            binding.itemetcIcon.setOnClickListener{
                onVisitClicked(bindingAdapterPosition)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LockerSavedAlbumRecycleAdapter.AlbumViewHolder {
        val binding = ItemSaveAlbumBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LockerSavedAlbumRecycleAdapter.AlbumViewHolder, position: Int) {
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

