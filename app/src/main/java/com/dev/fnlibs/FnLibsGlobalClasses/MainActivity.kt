package com.dev.fnlibs.FnLibsGlobalClasses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dev.fnlibs.R
import com.dev.fnlibs.AndroidNotifications.activities.AndroidNotifications
import com.dev.fnlibs.NotesWithRoomDB.Activities.ActivityNotes
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonNotifications.setOnClickListener {
            val intent = Intent(applicationContext, AndroidNotifications::class.java)
            startActivity(intent)
        }

        buttonNotes.setOnClickListener {
            val intent = Intent(applicationContext, ActivityNotes::class.java)
            startActivity(intent)
        }
    }
}