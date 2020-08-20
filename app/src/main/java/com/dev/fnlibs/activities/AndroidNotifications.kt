package com.dev.fnlibs.activities

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.dev.fnlibs.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_android_notifications.*

class AndroidNotifications : AppCompatActivity() {

    val CHANNEL_ID: String? = "ernest_dev"
    val CHANNEL_NAME: String? = "ernest dev"
    val CHANNEL_DESC: String? = "ernest.dev"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_notifications)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val channel: NotificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            channel.description = CHANNEL_DESC

            val nManager: NotificationManager = getSystemService(NotificationManager::class.java)
            nManager.createNotificationChannel(channel)
        }

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("TAG", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token.toString()

                fcmTokenTV.text = token

                // Log and toast
                Log.d("TAG", token)
                Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
            })

    }

    @SuppressLint("WrongConstant")
    private fun displayNotification() {
        val mBuilder: Notification.Builder = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Notification.Builder(applicationContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("New Notification")
                .setContentText("Hi, Please click me....your first notification")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(applicationContext)

        notificationManager.notify(100, mBuilder.build())
    }
}