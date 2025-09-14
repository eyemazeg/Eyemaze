package com.eyemazegraphics

import android.app.Application
import com.eyemazegraphics.database.AppDatabase
import com.eyemazegraphics.utils.NotificationHelper

class EyemazeApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        NotificationHelper(this).createCashTransactionNotificationChannel()
    }
}
