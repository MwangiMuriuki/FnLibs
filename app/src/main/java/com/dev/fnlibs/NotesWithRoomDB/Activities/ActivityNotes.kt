package com.dev.fnlibs.NotesWithRoomDB.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.dev.fnlibs.R
import kotlinx.android.synthetic.main.activity_profile.*

class ActivityNotes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val navController = Navigation.findNavController(this, R.id.nav_host_frag)
        NavigationUI.setupActionBarWithNavController(this, navController)



    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.nav_host_frag),
            null
        )
    }
}