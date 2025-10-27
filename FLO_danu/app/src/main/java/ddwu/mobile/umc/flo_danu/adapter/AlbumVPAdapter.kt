package ddwu.mobile.umc.flo_danu.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ddwu.mobile.umc.flo_danu.data.Album
import ddwu.mobile.umc.flo_danu.vp.album.DetailFragment
import ddwu.mobile.umc.flo_danu.vp.album.SongFragment
import ddwu.mobile.umc.flo_danu.vp.album.VideoFragment

class AlbumVPAdapter(fragment: Fragment, private val album: Album): FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> SongFragment()
            1 -> {
                val detailFragment = DetailFragment()
                val bundle = Bundle().apply {
                    putSerializable("Lilac", album)
                }
                detailFragment.arguments = bundle
                detailFragment
            }
            else -> VideoFragment()
        }
    }

    override fun getItemCount(): Int = 3
}