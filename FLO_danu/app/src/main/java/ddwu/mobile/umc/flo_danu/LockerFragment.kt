package ddwu.mobile.umc.flo_danu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import ddwu.mobile.umc.flo_danu.adapter.LockerVPAdapter
import ddwu.mobile.umc.flo_danu.databinding.FragmentLockerBinding

class LockerFragment: Fragment() {

    lateinit var binding: FragmentLockerBinding

    private val tabInfo = arrayListOf("저장한 곡", "음악 파일", "저장한 앨범")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerBinding.inflate(inflater, container, false)




        val lockerAdapter = LockerVPAdapter(this)
        binding.lockerVp.adapter = lockerAdapter
        TabLayoutMediator(binding.lockerTb, binding.lockerVp) {
                tab, position ->
            tab.text = tabInfo[position]
        }.attach()

        return binding.root
    }
}