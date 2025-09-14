package com.eyemazegraphics.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import com.eyemazegraphics.R

class NotificationHelper(private val context: Context) {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    companion object {
        private const val CASH_TRANSACTION_CHANNEL_ID = "cash_transaction_channel"
        private const val CASH_TRANSACTION_CHANNEL_NAME = "Cash Transactions"
    }

    fun createCashTransactionNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val soundUri = Uri.parse("android.resource://${context.packageName}/${R.raw.notification_sound}")
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()

            val channel = NotificationChannel(
                CASH_TRANSACTION_CHANNEL_ID,
                CASH_TRANSACTION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for cash transactions"
                setSound(soundUri, audioAttributes)
                enableVibration(true)
                vibrationPattern = longArrayOf(0, 500, 200, 500)
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showCashTransactionNotification(message: String) {
        val builder = NotificationCompat.Builder(context, CASH_TRANSACTION_CHANNEL_ID)
            .setContentTitle("EYEMAZE GRAPHICS")
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_logo) // Using the logo icon
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        notificationManager.notify(2, builder.build()) // Using a different ID
    }
}
