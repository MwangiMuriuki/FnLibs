package com.dev.fnlibs.FnLibsGlobalClasses

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.dev.fnlibs.R

class SplashScreen : AppCompatActivity() {
    val SPLASH_SCREEN_TIME_OUT = 4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

         Handler().postDelayed({
                val intent: Intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
        }, SPLASH_SCREEN_TIME_OUT.toLong());
    }

}