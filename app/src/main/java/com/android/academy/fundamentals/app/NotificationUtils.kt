package com.android.academy.fundamentals.app

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

private const val CHANNEL_ID = "ChannelId"

@RequiresApi(Build.VERSION_CODES.O)
fun createChannel(context: Context): NotificationChannel {
    return NotificationChannel(
        CHANNEL_ID,
        context.getString(R.string.ws03_service_channel_name),
        NotificationManager.IMPORTANCE_DEFAULT
    ).apply {
        description = context.getString(R.string.ws03_service_channel_description)
        setSound(null, null)
        enableVibration(false)
    }
}

fun createNotification(
    context: Context,
    title: String,
    pendingIntent: PendingIntent? = null
): Notification {
    return NotificationCompat.Builder(context, CHANNEL_ID)
        .setContentTitle(title)
        .setSmallIcon(R.drawable.ic_ws03_service_icon)
        .also { notificationBuilder ->
            if (pendingIntent != null) {
                notificationBuilder.setContentIntent(pendingIntent)
            }
        }
        .build()
}
