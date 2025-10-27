package ddwu.mobile.umc.flo_danu.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BannerVPAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    private val fragmentlist: ArrayList<Fragment> = ArrayList()

    override fun createFragment(position: Int): Fragment =fragmentlist[position]

    override fun getItemCount(): Int = fragmentlist.size

    fun addFragment(fragment: Fragment) {
        fragmentlist.add(fragment)
        notifyItemInserted(fragmentlist.size-1)
    }
}