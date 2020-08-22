package com.dev.fnlibs.activities.AndroidNotifications.helperClasses

import android.annotation.SuppressLint
import android.app.Application
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.dev.fnlibs.R
import com.dev.fnlibs.activities.AndroidNotifications.activities.AndroidNotifications

class NotificationHelper{
    companion object{
        val CHANNEL_ID: String? = "ernest_dev"
        val CHANNEL_NAME: String? = "ernest dev"
        val CHANNEL_DESC: String? = "ernest.dev"

        @SuppressLint("WrongConstant")
        fun displayNotification(context: Context, title: String, message: String) : Unit {

            val intent = Intent(context, AndroidNotifications::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 100, intent, PendingIntent.FLAG_CANCEL_CURRENT)

            val mBuilder: Notification.Builder = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                Notification.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            val notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(context)

            notificationManager.notify(100, mBuilder.build())
        }

    }

}