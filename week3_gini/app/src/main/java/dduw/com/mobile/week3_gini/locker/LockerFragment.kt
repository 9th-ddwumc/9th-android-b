package dduw.com.mobile.week3_gini.locker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dduw.com.mobile.week3_gini.databinding.FragmentLockerBinding
import com.google.android.material.tabs.TabLayoutMediator

class LockerFragment : Fragment() {

    lateinit var binding: FragmentLockerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //ViewPager2에 어댑터 연결
        //Adaptor는 아까 작성한 LockerViewApadtor
        binding.vpLockerLockerFragment.adapter = LockerViewAdaptor(this)

        // TabLayout과 ViewPager2 연결
        TabLayoutMediator(binding.tblStorelayLockerFragment, binding.vpLockerLockerFragment) { tab, position ->
            tab.text = when (position) {
                0 -> "저장한 곡"
                1 -> "음악파일"
                2 -> "저장앨범"
                else -> ""
            }
        }.attach()

    }
}