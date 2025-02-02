package com.drm.allinone

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.drm.allinone.adapter.UserAdapter
import com.drm.allinone.databinding.ActivityThirdBinding
import com.drm.allinone.utils.goToActivityWithFinish
import com.drm.allinone.viewpager.NormalViewPagerAdapter
import com.google.firebase.auth.FirebaseAuth

class ThirdActivity : AppCompatActivity() {

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

    //To render some data on listview, we need 3 things
    // 1) Data source - arraylist
    // 2) View of each item - item_listview.xml
    // 3) Adapter to bind that view with data source
    // 4) Layout Manager


    //Horizontal views, Vertical view, Grid View


    //to maintain checklist
    var userList = arrayListOf<User>()

    private fun setUI() = binding.apply {



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
            GridLayoutManager(this@ThirdActivity, 2)

        recyclerView.adapter = UserAdapter(this@ThirdActivity, userList)


        val imageList = ArrayList<Int>()

        imageList.add(R.mipmap.image1)
        imageList.add(R.mipmap.image2)
        imageList.add(R.mipmap.image3)
        imageList.add(R.mipmap.image2)
        imageList.add(R.mipmap.image3)
        imageList.add(R.mipmap.image1)



        viewPager.adapter = NormalViewPagerAdapter(this@ThirdActivity, imageList)


    }


    private fun setListeners() = binding.apply {

        logout.setOnClickListener {
            firebaseAuth.signOut()
            goToActivityWithFinish(SecondActivity::class.java)
        }
    }


}
