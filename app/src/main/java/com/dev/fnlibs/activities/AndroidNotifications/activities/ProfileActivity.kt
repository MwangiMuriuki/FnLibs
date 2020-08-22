package com.dev.fnlibs.activities.AndroidNotifications.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dev.fnlibs.R
import com.dev.fnlibs.activities.AndroidNotifications.dataClasses.UserDataClass
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        profileToolbar.setNavigationIcon(R.drawable.arrow_back)
        setSupportActionBar(profileToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("TAG", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token.toString()

                saveDeviceToken(token)

                // Log and toast
                Log.d("TAG", token)
                Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
            })
    }

    private fun saveDeviceToken(token: String) {
        val email = firebaseAuth.currentUser?.email

        val userDataClass: UserDataClass = UserDataClass(email, token)

        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Users")
        myRef.child(firebaseAuth.currentUser?.uid!!).setValue(userDataClass)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(baseContext, "User saved", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(baseContext, it.message, Toast.LENGTH_SHORT).show()
            }
    }
}