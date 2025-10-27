package ddwu.mobile.umc.flo_danu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ddwu.mobile.umc.flo_danu.data.Album
import ddwu.mobile.umc.flo_danu.databinding.ItemAlbumBinding

class AlbumRVAdapter(
    private val albumList: List<Album>,
    private val onItemClick: (Album) -> Unit = {},
    private val onPlayClick: (Album) -> Unit = {},
) : RecyclerView.Adapter<AlbumRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AlbumRVAdapter.ViewHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = albumList[position]
        holder.bind(album)
        holder.itemView.setOnClickListener { onItemClick(album) }
    }

    override fun getItemCount(): Int = albumList.size

    inner class ViewHolder(val binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Album) {
            binding.itemAlbumTitleTv.text = item.title
            binding.itemAlbumSingerTv.text = item.singer
            item.coverImg?.let { binding.itemAlbumCoverImgIv.setImageResource(it) }

            binding.itemAlbumPlayImgIv.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    onPlayClick(item)
                }
            }
        }
    }
}