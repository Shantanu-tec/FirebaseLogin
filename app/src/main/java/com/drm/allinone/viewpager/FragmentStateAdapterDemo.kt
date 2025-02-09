package com.drm.allinone.viewpager

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.drm.allinone.models.FragmentModels

class FragmentStateAdapterDemo(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    val fragmentList = mutableListOf<FragmentModels>()

    fun addFragment(fragment: FragmentModels) {
        fragmentList.add(fragment)
    }

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position].fragment


}