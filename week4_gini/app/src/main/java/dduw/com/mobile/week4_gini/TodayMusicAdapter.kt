package dduw.com.mobile.week4_gini

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dduw.com.mobile.week4_gini.album.AlbumFragment
import dduw.com.mobile.week4_gini.data.TodayMusicData
import dduw.com.mobile.week4_gini.databinding.ItemHomeTodayMusicBinding

class TodayMusicAdapter(private val itemList: List<TodayMusicData>) :
    RecyclerView.Adapter<TodayMusicAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemHomeTodayMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TodayMusicData) {
            binding.itemAlbumImg.setImageResource(item.albumImg)
            binding.itemAlbumTitle.text = item.title
            binding.itemAlbumSinger.text = item.singer
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
        (holder.itemView.context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, AlbumFragment())
            .commitAllowingStateLoss()
    }
}