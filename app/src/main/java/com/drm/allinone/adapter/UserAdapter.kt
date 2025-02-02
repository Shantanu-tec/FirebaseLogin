package com.drm.allinone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drm.allinone.ThirdActivity
import com.drm.allinone.User
import com.drm.allinone.databinding.ItemListviewBinding
import com.drm.allinone.utils.printDebug

class UserAdapter(
    private val context: Context,
    private val userList: ArrayList<User>
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemListviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemListviewBinding.inflate(LayoutInflater.from(context), parent, false)
    )

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            name.text = userList[position].name
            graduation.text = userList[position].graduation
//            printDebug("Before onClick isChecked at position $position : ${userList[position].isChecked}")
            checkbox.isChecked = userList[position].isChecked

            main.setOnClickListener {
//                userList[position].isChecked = !userList[position].isChecked
                userList.forEachIndexed { index, user ->
                    user.isChecked = index == position
                }
                notifyDataSetChanged()
            }
        }

    }


}