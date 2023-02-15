package com.ericwathome.currencybuddy.common.util

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.ericwathome.currencybuddy.R
import com.ericwathome.currencybuddy.common.AppConstants

object NotificationUtils {
    fun showNotification(
        context: Context,
        title: String,
        text: String
    ) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val notification = NotificationCompat.Builder(context, AppConstants.SYNC_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(R.drawable.ic_sync_24)
            .build()
        notificationManager.notify(AppConstants.SYNC_NOTIFICATION_ID, notification)
    }
}