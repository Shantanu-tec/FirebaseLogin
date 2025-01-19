package com.drm.allinone

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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


    }


    private fun setListeners() = binding.apply {
        if (firebaseAuth.currentUser != null){
            if (firebaseAuth.currentUser?.email.isNullOrEmpty()){
                userEmail.text =  firebaseAuth.currentUser?.phoneNumber
            }else{
                userEmail.text = firebaseAuth.currentUser?.email
            }

        }else{
            goToActivityWithFinish(SecondActivity::class.java)
        }


        logout.setOnClickListener {
            firebaseAuth.signOut()
            goToActivityWithFinish(SecondActivity::class.java)
        }
    }






}
