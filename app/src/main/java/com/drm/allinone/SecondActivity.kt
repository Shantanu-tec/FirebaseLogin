package com.drm.allinone


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.drm.allinone.databinding.ActivitySecondBinding
import com.drm.allinone.fragments.ChooserFragment
import com.drm.allinone.fragments.LoginFragment

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        addFragment()
//        replaceFragment(LoginFragment(),false)

    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)

        if (fragment != null){
            when (fragment) {
                is ChooserFragment -> {
                    finish()
                }

                is LoginFragment -> {
                    replaceFragment(ChooserFragment())
                }

                else -> {
                    super.onBackPressed()
                }
            }
        }

    }


    //add/replace

    private fun addFragment(){
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.add(R.id.fragmentContainer,ChooserFragment())
        transaction.commit()
    }


    fun replaceFragment(fragment: Fragment, isAddToBackStack : Boolean = true){
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragmentContainer,fragment)
        if (isAddToBackStack) {
            transaction.addToBackStack(fragment.javaClass.simpleName)
        }
        transaction.commit()
    }





}



