package com.mobile.week4.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PannelVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val imgList: ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int = imgList.size

    override fun createFragment(position: Int): Fragment = imgList[position]

    fun addImg(fragment: Fragment) {
        imgList.add(fragment)
        notifyItemInserted(imgList.size - 1)
    }
}