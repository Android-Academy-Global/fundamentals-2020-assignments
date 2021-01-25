package com.android.academy.fundamentals.app.workshop03

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.android.academy.fundamentals.app.R

private const val CHANNEL_ID = "ChannelId"

class WS03NotificationManager {

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationForOreo(
        context: Context,
        title: String,
        pendingIntent: PendingIntent? = null
    ): Notification {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(createChannel(context))

        return createNotification(context, title, pendingIntent)
    }

    fun createNotificationAfterJobDone(
        context: Context,
        resultFileUri: Uri,
    ) {
        val notifyIntent = Intent(context, WS03ResultActivity::class.java)
            .apply {
                flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                putExtra(WS03ResultActivity.KEY_IMAGE_URI, resultFileUri)
            }

        val notifyPendingIntent = PendingIntent.getActivity(
            context,
            0,
            notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val createNotification = createNotification(
            context,
            "Done",
            notifyPendingIntent
        )
        NotificationManagerCompat.from(context)
            .notify(NOTIFICATION_ID, createNotification)
    }

    fun updateNotification(context: Context, title: String) {
        val notification = createNotification(context, title)

        NotificationManagerCompat.from(context)
            .notify(NOTIFICATION_ID, notification)
    }

    private fun createNotification(
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(context: Context): NotificationChannel {
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

    companion object {
        const val NOTIFICATION_ID = 1
    }
}
