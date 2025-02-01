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

    private fun setUI() = binding.apply {
        val userList = arrayListOf<User>()

        userList.add(User(R.mipmap.ic_launcher, "Rahul", "Post Graduate"))
        userList.add(User(R.mipmap.ic_launcher, "Kumar", "Post Graduate"))
        userList.add(User(R.mipmap.ic_launcher, "Tarun", "Under Graduation"))
        userList.add(User(R.mipmap.ic_launcher, "Karan", "Under Graduation"))
        userList.add(User(R.mipmap.ic_launcher, "Shantanu", "Working"))
        userList.add(User(R.mipmap.ic_launcher, "Chaudhary", "Working"))


//        for (i in 1..100){
//            userList.add(User(R.mipmap.ic_launcher, "User $i", "Learning"))
//        }


        recyclerView.layoutManager =
            GridLayoutManager(this@ThirdActivity, 2)

        recyclerView.adapter = UserAdapter(this@ThirdActivity, userList)


    }


    private fun setListeners() = binding.apply {

        logout.setOnClickListener {
            firebaseAuth.signOut()
            goToActivityWithFinish(SecondActivity::class.java)
        }
    }


}
