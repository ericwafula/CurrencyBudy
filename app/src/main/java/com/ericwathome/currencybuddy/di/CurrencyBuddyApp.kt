package com.ericwathome.currencybuddy.di

import android.app.Application
import com.ericwathome.currencybuddy.BuildConfig
import tech.ericwathome.presentation.util.NotificationUtils
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CurrencyBuddyApp : Application(), NotificationUtils.InitNotifications by NotificationUtils.Notifications() {
    companion object {
        val TAG = this::class.simpleName
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        createNotificationChannel(this)
    }
}