package com.ericwathome.currencybuddy.di

import android.app.Application
import android.os.Handler
import android.os.Looper
import com.ericwathome.currencybuddy.BuildConfig
import com.ericwathome.currencybuddy.exceptions.CrashListener
import com.google.firebase.crashlytics.FirebaseCrashlytics
import tech.ericwathome.presentation.util.NotificationUtils
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CurrencyBuddyApp : Application(),
    NotificationUtils.InitNotifications by NotificationUtils.Notifications(), CrashListener {
    companion object {
        val TAG = this::class.simpleName
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        createNotificationChannel(this)
        setupCrashHandler()
    }

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        NotificationUtils.showNotification(
            this,
            "Something went wrong!",
            "We are working on it"
        )
        Timber.tag("$TAG").e("$throwable")
        FirebaseCrashlytics.getInstance().recordException(throwable)
        throwable.message?.let {
            FirebaseCrashlytics.getInstance().log(it)
        }
    }

    private fun setupCrashHandler() {
        Handler(Looper.getMainLooper()).post {
            while (true) {
                try {
                    Looper.loop()
                } catch (e: Throwable) {
                    uncaughtException(Looper.getMainLooper().thread, e)
                }
            }
        }
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            uncaughtException(t, e)
        }
    }
}