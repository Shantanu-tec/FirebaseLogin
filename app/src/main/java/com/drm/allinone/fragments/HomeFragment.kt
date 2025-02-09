package com.drm.allinone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.drm.allinone.R
import com.drm.allinone.User
import com.drm.allinone.adapter.UserAdapter
import com.drm.allinone.databinding.FragmentHomeBinding
import com.drm.allinone.viewpager.NormalViewPagerAdapter
import com.drm.allinone.viewpager.ViewPager2AdapterDemo
import com.google.android.material.tabs.TabLayout.Tab
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    private var userList = arrayListOf<User>()
    private val imageList = ArrayList<Int>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUI()
    }

    private fun setUI() = binding.apply {


        userList.clear()

        userList.add(User(R.mipmap.ic_launcher, "Joggers", "Post Graduate",false))
        userList.add(User(R.mipmap.ic_launcher, "Track suits", "Post Graduate",false))
        userList.add(User(R.mipmap.ic_launcher, "T-shirts", "Under Graduation",false))
        userList.add(User(R.mipmap.ic_launcher, "Shirts", "Under Graduation",false))
        userList.add(User(R.mipmap.ic_launcher, "Pants", "Working",false))
        userList.add(User(R.mipmap.ic_launcher, "Yoga pants", "Working",false))


//        for (i in 1..100){
//            userList.add(User(R.mipmap.ic_launcher, "User $i", "Learning",false))
//        }


        recyclerView.layoutManager =
            GridLayoutManager(requireActivity(), 2)

        recyclerView.adapter = UserAdapter(requireActivity(), userList)


        imageList.clear()

        imageList.add(R.mipmap.image1)
        imageList.add(R.mipmap.image2)
        imageList.add(R.mipmap.image3)
        imageList.add(R.mipmap.image2)
        imageList.add(R.mipmap.image3)
        imageList.add(R.mipmap.image1)



//        viewPager.adapter = NormalViewPagerAdapter(requireActivity(), imageList)
        viewPager.adapter = ViewPager2AdapterDemo(requireActivity(),imageList)

        setAutoScroll(true)

    }

    private val autoScrollCoroutine = CoroutineScope(Dispatchers.Main)

    private var currentPosition = 0

    private fun setAutoScroll(autoScroll:Boolean) = binding.apply{
        autoScrollCoroutine.launch {
            while (isActive && autoScroll) {
                delay(2000)
                if (currentPosition<imageList.size){
                    viewPager.currentItem = ++currentPosition
                }else{
                    viewPager.currentItem = 0
                    currentPosition = 0
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        autoScrollCoroutine.cancel("")
    }

}