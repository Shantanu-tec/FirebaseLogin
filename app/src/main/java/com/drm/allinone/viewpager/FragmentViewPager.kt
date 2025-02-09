package com.drm.allinone.viewpager


import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.drm.allinone.models.FragmentModels

class FragmentViewPager(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val fragmentList = mutableListOf<FragmentModels>()

    fun addFragment(fragment: FragmentModels){
        fragmentList.add(fragment)
    }

    override fun getCount() = fragmentList.size

    override fun getItem(position: Int) = fragmentList[position].fragment

    override fun getPageTitle(position: Int) = fragmentList[position].title

}