package com.dev.fnlibs.activities.AndroidNotifications.activities

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.dev.fnlibs.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_android_notifications.*


class AndroidNotifications : AppCompatActivity() {

    val CHANNEL_ID: String? = "ernest_dev"
    val CHANNEL_NAME: String? = "ernest dev"
    val CHANNEL_DESC: String? = "ernest.dev"

    val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_notifications)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val channel: NotificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = CHANNEL_DESC

            val nManager: NotificationManager = getSystemService(NotificationManager::class.java)
            nManager.createNotificationChannel(channel)
        }

        signupBtn.setOnClickListener {
            createUser()
        }

        loginBtn.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email = loginEmailTV.text.toString()
        val password = loginPasswordTV.text.toString()

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Login_TAG", "signInWithEmail:success")
                    val user = mAuth.currentUser
                    val intent = Intent(applicationContext, ProfileActivity::class.java)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Login_TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        this@AndroidNotifications, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                // ...
            }
    }

    private fun createUser() {
        val email = emailTV.text.toString()
        val password = passwordTV.text.toString()

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val user = mAuth.currentUser

                    // Write a message to the database
                    val database = FirebaseDatabase.getInstance()
                    val myRef = database.getReference("Users")

                    myRef.setValue(email)

                    val intent = Intent(applicationContext, ProfileActivity::class.java)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                }else{

                    Toast.makeText(
                        baseContext, "Authentication failed: " + it.exception?.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }


}