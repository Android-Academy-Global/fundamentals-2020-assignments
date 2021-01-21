package com.android.academy.fundamentals

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import com.android.academy.fundamentals.app.R
import com.android.academy.fundamentals.app.blurBitmap
import com.android.academy.fundamentals.app.workshop03.WS03ResultActivity
import com.android.academy.fundamentals.app.workshop03.WS03ResultActivity.Companion.KEY_IMAGE_URI
import com.android.academy.fundamentals.app.writeBitmapToFile
import kotlinx.coroutines.*


/**
 * @author y.anisimov
 */
class WS03Service : Service() {
    private var coroutineScope: CoroutineScope = createCoroutineScope()
    private var payloadJob: Job? = null

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

        // PShchahelski
        // Я помню есть NotificationsCompat, а там нету для channel чего?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notification: Notification = Notification.Builder(this, CHANNEL_ID)
                .setContentTitle(getText(R.string.ws03_service_title))
                .setSmallIcon(R.drawable.ic_ws03_service_icon)
                .build()

            startForeground(NOTIFICATION_ID, notification)
        }
    }

    override fun onDestroy() {
        coroutineScope.cancel("It's time")
        super.onDestroy()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (payloadJob?.isActive == true) {
            payloadJob?.cancel()
        }
        payloadJob = coroutineScope.launch(context = exceptionHandler) {

            val picture = BitmapFactory.decodeStream(this@WS03Service.assets.open(DEFAULT_FILE_NAME))
            val output = blurBitmap(picture, this@WS03Service)
            val resultFileUri = writeBitmapToFile(this@WS03Service, output)

            val notifyIntent = Intent(this@WS03Service, WS03ResultActivity::class.java).also {
                it.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                it.putExtra(KEY_IMAGE_URI, resultFileUri)
            }
            val notifyPendingIntent = PendingIntent.getActivity(
                this@WS03Service, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
            )

            createNotification("Done", notifyPendingIntent).replace()
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
    private fun createNotification(title: String, pendingIntent: PendingIntent? = null): Notification {
        return Notification.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_ws03_service_icon)
            .also { notificationBuilder ->
                if (pendingIntent != null) {
                    notificationBuilder.setContentIntent(pendingIntent)
                }
            }
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
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "ChannelId"
        private const val TAG = "WS03Service"

        private const val DEFAULT_FILE_NAME = "test.jpg"
    }
}
