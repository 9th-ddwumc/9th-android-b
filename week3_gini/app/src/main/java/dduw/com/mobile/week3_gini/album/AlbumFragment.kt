package dduw.com.mobile.week3_gini.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import dduw.com.mobile.week3_gini.HomeFragment
import dduw.com.mobile.week3_gini.R
import dduw.com.mobile.week3_gini.databinding.FragmentAlbumBinding


class AlbumFragment : Fragment() {

    lateinit var binding: FragmentAlbumBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater,container,false)

        binding.albumBackIv.setOnClickListener {
            parentFragmentManager.beginTransaction()    //액티비티 <-> 프레그먼트 (supportFrangmentManager)
                                                        //액티비티들의 자식 프레그먼트 <-> 프레그먼트 이동 (parentFramgnetMangerb         )
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //ViewPager2에 어댑터 연결
        binding.vpAlbumAlbumFragment.adapter = AlbumViewAdaptor(this)

        // TabLayout과 ViewPager2 연결
        TabLayoutMediator(binding.tablAlbumAlbumFragment, binding.vpAlbumAlbumFragment) { tab, position ->
            tab.text = when (position) {
                0 -> "수록곡"
                1 -> "상세정보"
                2 -> "영상"
                else -> ""
            }
        }.attach()

    }

}