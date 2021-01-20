package com.android.academy.fundamentals

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import com.android.academy.fundamentals.app.R
import kotlinx.coroutines.*

/**
 * @author y.anisimov
 */
class WS03Service : Service() {
    private var coroutineScope = createCoroutineScope()

    private val exceptionHandler: CoroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { _, throwable ->
            Log.e(TAG, "Coroutine exception, scope active:${coroutineScope.isActive}", throwable)
            coroutineScope = createCoroutineScope()
        }
    }

    override fun onBind(p0: Intent?): IBinder? = null
    // permission
    // manifest
    // pending intent

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        initChannels(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notification: Notification = Notification.Builder(this, CHANNEL_ID)
                .setContentTitle(getText(R.string.ws03_service_title))
                .setSmallIcon(R.drawable.ic_ws03_service_icon)
                .build()

            startForeground(WS03_SERVICE_STATUS, notification)
        }
    }

    override fun onDestroy() {
//        coroutineScope.cancel("It's time")
        super.onDestroy()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        coroutineScope.cancel()
        coroutineScope.launch(context = exceptionHandler) {
            delay(1_000)
            createNotification("1").replace()
            delay(1_000)
            createNotification("2").replace()
            delay(1_000)
            createNotification("3").replace()
        }
        return START_NOT_STICKY
    }

    private fun createCoroutineScope() = CoroutineScope(Job() + Dispatchers.IO)

    private fun Notification.replace() {
        NotificationManagerCompat.from(this@WS03Service).apply {
            notify(NOTIFICATION_ID, this@replace)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotification(title: String): Notification {
        return Notification.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_ws03_service_icon)
            .build()
    }

    private fun initChannels(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            CHANNEL_ID,
            getString(R.string.ws03_service_channel_name),
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = getString(R.string.ws03_service_channel_description)
        channel.setSound(null, null)
        channel.enableVibration(false)
        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        private const val WS03_SERVICE_STATUS = 1
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "ChannelId"
        private const val TAG = "WS03Service"
    }
}
