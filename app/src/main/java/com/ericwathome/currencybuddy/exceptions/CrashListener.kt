package com.ericwathome.currencybuddy.exceptions

interface CrashListener {
    fun uncaughtException(thread: Thread, throwable: Throwable)
}