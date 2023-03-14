package tech.ericwathome.presentation.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import tech.ericwathome.presentation.R

object NotificationUtils {

    interface InitNotifications {
        fun createNotificationChannel(context: Context)
    }

    class Notifications : InitNotifications {
        override fun createNotificationChannel(context: Context) {
            val channel = NotificationChannel(
                NotificationConstants.SYNC_CHANNEL_ID,
                NotificationConstants.SYNC_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showNotification(
        context: Context,
        title: String,
        text: String
    ) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val notification = NotificationCompat.Builder(context, NotificationConstants.SYNC_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(R.drawable.ic_sync_24)
            .build()
        notificationManager.notify(NotificationConstants.SYNC_NOTIFICATION_ID, notification)
    }
}