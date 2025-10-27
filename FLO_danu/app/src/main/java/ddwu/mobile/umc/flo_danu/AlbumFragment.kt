package ddwu.mobile.umc.flo_danu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import ddwu.mobile.umc.flo_danu.adapter.AlbumVPAdapter
import ddwu.mobile.umc.flo_danu.data.Album
import ddwu.mobile.umc.flo_danu.databinding.FragmentAlbumBinding

class AlbumFragment: Fragment() {

    lateinit var binding: FragmentAlbumBinding

    private val tabInfo = listOf("수록곡", "상세정보", "영상")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)

        val lilac = arguments?.getSerializable("Lilac") as? Album

        lilac!!.coverImg?.let { binding.albumImgIv.setImageResource(it) }
        binding.albumTitleTv.setText(lilac.title)
        binding.albumSingerTv.setText(lilac.singer)

        binding.albumBackIb.setOnClickListener {
            parentFragmentManager.popBackStack()
        }


        val albumAdapter = AlbumVPAdapter(this, lilac)
        binding.albumContentVp.adapter = albumAdapter
        TabLayoutMediator(binding.albumContentTb, binding.albumContentVp) {
                tab, position ->
            tab.text = tabInfo[position]
        }.attach()

        return binding.root
    }
}