package com.drm.allinone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.drm.allinone.adapter.PostAdapter
import com.drm.allinone.apicall.RetroFitInstance
import com.drm.allinone.databinding.FragmentFeedsBinding
import com.drm.allinone.models.ResponseData
import com.drm.allinone.utils.showToastLong
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedsFragment : Fragment() {

    private lateinit var binding: FragmentFeedsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }


    private fun getData() {
        RetroFitInstance.apiInterface.getPostByPath("1").enqueue(object : Callback<ResponseData> {
            override fun onResponse(
                call: Call<ResponseData>,
                response: Response<ResponseData>
            ) {
                val post = response.body()
                val postList = ArrayList<ResponseData>()
                postList.add(post!!)
                binding.postRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.postRecyclerView.adapter = PostAdapter(requireContext(), postList)
            }

            override fun onFailure(call: Call<ResponseData>, error: Throwable) {
                requireActivity().showToastLong(error.message.toString())
            }

        })

    }
}