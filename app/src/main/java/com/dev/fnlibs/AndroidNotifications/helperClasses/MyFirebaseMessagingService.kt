package com.dev.fnlibs.AndroidNotifications.helperClasses

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

//    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
//        super.onMessageReceived(remoteMessage)
//        val notification = remoteMessage?.notification
//
//        if (notification != null){
//            val title : String? = notification.title
//            val message : String? = notification.body
//
//            NotificationHelper.displayNotification(applicationContext, title!!, message!!)
//        }
//    }
}