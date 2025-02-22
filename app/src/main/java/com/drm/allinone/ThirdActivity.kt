package com.drm.allinone

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.drm.allinone.databinding.ActivityThirdBinding
import com.drm.allinone.fragments.ChatFragment
import com.drm.allinone.fragments.FeedsFragment
import com.drm.allinone.fragments.HomeFragment
import com.drm.allinone.models.FragmentModels
import com.drm.allinone.utils.goToActivityWithFinish
import com.drm.allinone.viewpager.FragmentStateAdapterDemo
import com.drm.allinone.viewpager.FragmentViewPager
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth

class ThirdActivity : AppCompatActivity() {

    //Android Application : Screen to the end user.
    //Backend : httpCall
    //API : http://baseUrl/V1/loginUser
    //Traditional Http Call : Retrofit
    //Asynchronous : Multiple Api Call
    //Synchronous.
    // Clean and simple
    // Type Safe. Direct JSON/Rest api into Model Classes. Converter.
    // Headers

    private lateinit var binding: ActivityThirdBinding

    private var firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setListeners()

        setUI()

    }


    //ViewPager PagerAdapter and FragmentStatePagerAdapter
    //FragmentStatePagerAdapter with TabLayout

    //ViewPager2
    //FragmentStateAdapter



    private fun setUI() = binding.apply {
        tabLayout.tabTextColors =
            ColorStateList.valueOf(ResourcesCompat.getColor(resources, R.color.black, theme))
        val homeTab =
            tabLayout.newTab().setText(ContextCompat.getString(this@ThirdActivity, R.string.home))
        val feedsTab =
            tabLayout.newTab().setText(ContextCompat.getString(this@ThirdActivity, R.string.feeds))
        val chatsTab =
            tabLayout.newTab().setText(ContextCompat.getString(this@ThirdActivity, R.string.chats))

        tabLayout.addTab(homeTab)
        tabLayout.addTab(feedsTab)
        tabLayout.addTab(chatsTab)


        val adapter = FragmentStateAdapterDemo(supportFragmentManager,lifecycle)

        adapter.addFragment(
            FragmentModels(
                ContextCompat.getString(
                    this@ThirdActivity,
                    R.string.home
                ), HomeFragment()
            )
        )
        adapter.addFragment(
            FragmentModels(
                ContextCompat.getString(
                    this@ThirdActivity,
                    R.string.feeds
                ), FeedsFragment()
            )
        )
        adapter.addFragment(
            FragmentModels(
                ContextCompat.getString(
                    this@ThirdActivity,
                    R.string.chats
                ), ChatFragment()
            )
        )


        fragmentViewPager.adapter = adapter

        TabLayoutMediator(tabLayout,fragmentViewPager) { tab, position ->
            tab.text = adapter.fragmentList[position].title
        }.attach()



//        fragmentViewPager.setAdapter(adapter)
//
//        tabLayout.setupWithViewPager(fragmentViewPager)


    }


    //Adapters : PagerAdapter,FragmentStateAdapter


    private fun setListeners() = binding.apply {
        logout.setOnClickListener {
            firebaseAuth.signOut()
            goToActivityWithFinish(SecondActivity::class.java)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    //Maintain
    //Test cases
    //Bug fixes



}
