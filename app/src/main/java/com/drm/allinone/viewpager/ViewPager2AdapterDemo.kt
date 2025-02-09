package com.drm.allinone.viewpager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drm.allinone.databinding.ItemImageviewBinding

class ViewPager2AdapterDemo(private val context: Context,
    private val imageList: ArrayList<Int>): RecyclerView.Adapter<ViewPager2AdapterDemo.ViewHolder>() {
    class ViewHolder(val binding: ItemImageviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemImageviewBinding.inflate(LayoutInflater.from(context),parent,false)
    )

    override fun getItemCount() = imageList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.binding.apply {
          imageView.setImageResource(imageList[position])
      }
    }
}