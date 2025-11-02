package dduw.com.mobile.week5_gini

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dduw.com.mobile.week5_gini.data.TodayMusicData
import dduw.com.mobile.week5_gini.databinding.ItemHomeTodayMusicBinding

class TodayMusicAdapter(private val itemList: List<TodayMusicData>) :
    RecyclerView.Adapter<TodayMusicAdapter.ViewHolder>() {

    lateinit var onAlbumItemClicked: (TodayMusicData) -> Unit
    lateinit var onPlayButtonClicked: (TodayMusicData) -> Unit

    inner class ViewHolder(val binding: ItemHomeTodayMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: TodayMusicData,
            onAlbumClick: (TodayMusicData) -> Unit,
            onPlayClick: (TodayMusicData) -> Unit // 새 콜백
        ) {
            binding.itemAlbumImg.setImageResource(item.albumImg)
            binding.itemAlbumImg.setOnClickListener {
                onAlbumClick(item)
            }
            binding.itemAlbumTitle.text = item.title
            binding.itemAlbumSinger.text = item.singer
            binding.itemAlbumPlayIcon.setOnClickListener {
                onPlayClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeTodayMusicBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item, onAlbumItemClicked, onPlayButtonClicked)
    }


}