package com.ericwathome.currencybuddy.di

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.ericwathome.currencybuddy.common.util.Constants
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CurrencyBuddyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            Constants.CHANNEL_SYNC_ID,
            Constants.CHANNEL_SYNC_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )

        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}