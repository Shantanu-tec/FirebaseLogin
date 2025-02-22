package com.drm.allinone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drm.allinone.databinding.ItemPostBinding
import com.drm.allinone.models.ResponseData

class PostAdapter(
    private val context: Context,
    private val postList: List<ResponseData>
) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    inner class ViewHolder(val binding : ItemPostBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemPostBinding.inflate(LayoutInflater.from(context),parent,false)
    )

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            idText.text = postList[position].id
            userID.text = postList[position].userId
            titleText.text = postList[position].title
            bodyText.text = postList[position].body
        }
    }


}