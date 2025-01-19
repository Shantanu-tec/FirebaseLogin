package com.drm.allinone

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.drm.allinone.utils.printDebug
import com.drm.allinone.utils.showToastShort

class SixthActivity : AppCompatActivity() {

    lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sixth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        if (intent!=null){
            val data = intent.getStringExtra("data")
            showToastShort(data!!)
        }

        btn = findViewById(R.id.btn)

        btn.setOnClickListener {
            val intent = Intent(this,FourthActivity::class.java)
            intent.putExtra("data","Tarun")
            startActivity(intent)
        }

        printDebug("Activity F onCreate Called")
    }


    override fun onStart() {
        super.onStart()
        printDebug("Activity F onStart Called")
    }

    override fun onResume() {
        super.onResume()
        printDebug("Activity F onResume Called")
    }

    override fun onPause() {
        super.onPause()
        printDebug("Activity F onPause Called")
    }

    override fun onStop() {
        super.onStop()
        printDebug("Activity F onStop Called")
    }

    override fun onRestart() {
        super.onRestart()
        printDebug("Activity F onRestart Called")
    }


    override fun onDestroy() {
        super.onDestroy()
        printDebug("Activity F onDestroy Called")
    }
}