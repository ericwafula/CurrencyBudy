package com.ericwathome.currencybuddy.di

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import com.ericwathome.currencybuddy.core.util.AppConstants
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CurrencyBuddyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            AppConstants.SYNC_CHANNEL_ID,
            AppConstants.SYNC_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )

        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}