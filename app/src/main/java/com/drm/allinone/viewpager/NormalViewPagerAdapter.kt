package com.drm.allinone.viewpager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import com.drm.allinone.R

class NormalViewPagerAdapter(private val context: Context, private val imageList: ArrayList<Int>) :
    PagerAdapter() {


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.item_imageview, container, false)

        val imageView: ImageView = view.findViewById(R.id.imageView)

        imageView.setImageResource(imageList[position])


        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as CardView)
    }

    override fun getCount() = imageList.size

    override fun isViewFromObject(view: View, `object`: Any) = view == `object`
}