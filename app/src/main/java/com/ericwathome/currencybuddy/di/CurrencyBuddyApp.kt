package com.ericwathome.currencybuddy.di

import android.app.Application
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CurrencyBuddyApp : Application() {
    private lateinit var notificationManager: NotificationManager
    override fun onCreate() {
        super.onCreate()

        notificationManager = getSystemService(NotificationManager::class.java)

        createNotificationChannel()
    }

    private fun createNotificationChannel() {

    }
}