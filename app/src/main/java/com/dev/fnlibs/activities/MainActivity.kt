package com.dev.fnlibs.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dev.fnlibs.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonNotifications.setOnClickListener {
            val intent = Intent(applicationContext, AndroidNotifications::class.java)
            startActivity(intent)
        }
    }
}