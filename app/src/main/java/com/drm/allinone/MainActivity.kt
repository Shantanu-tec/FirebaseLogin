package com.drm.allinone


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.drm.allinone.databinding.ActivityMainBinding
import com.drm.allinone.utils.goToActivityWithFinish
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


//        Handler(Looper.getMainLooper()).postDelayed({
//            val intent = Intent(this@MainActivity, SecondActivity::class.java)
//            startActivity(intent)
//        },2000)

        //Scope: Activity, Fragment, Globally, ViewModel
        //Dispatchers: Main, IO, Default, Unconfined
        //Run : Launch, Async, RunBlocking


        FirebaseAnalytics.getInstance(this)
            .logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, Bundle().apply {
                putString("Name", "SplashActivity")
            })


        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)

            val firebaseAuth = FirebaseAuth.getInstance()
            if (firebaseAuth.currentUser != null) {
                goToActivityWithFinish(ThirdActivity::class.java)
            } else {
                goToActivityWithFinish(SecondActivity::class.java)
            }
        }


    }


}