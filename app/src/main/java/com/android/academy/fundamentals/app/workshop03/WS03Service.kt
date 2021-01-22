package com.android.academy.fundamentals

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import com.android.academy.fundamentals.app.*
import com.android.academy.fundamentals.app.workshop03.WS03ResultActivity
import com.android.academy.fundamentals.app.workshop03.WS03ResultActivity.Companion.KEY_IMAGE_URI
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

    // We don't provide binding, so return null
    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(createChannel(this))

            val notification = createNotification(this, getString(R.string.ws03_service_title))
            startForeground(NOTIFICATION_ID, notification)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (payloadJob?.isActive == true) {
            payloadJob?.cancel()
        }
        payloadJob = coroutineScope.launch(context = exceptionHandler) {
            val resultFileUri = blurAndSaveToFile()

            val notifyIntent = Intent(this@WS03Service, WS03ResultActivity::class.java).also {
                it.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                it.putExtra(KEY_IMAGE_URI, resultFileUri)
            }
            val notifyPendingIntent = PendingIntent.getActivity(
                this@WS03Service, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
            )

            val createNotification = createNotification(this@WS03Service, "Done", notifyPendingIntent)
            NotificationManagerCompat.from(this@WS03Service).notify(NOTIFICATION_ID, createNotification)
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        coroutineScope.cancel("It's time")
        super.onDestroy()
    }

    private suspend fun blurAndSaveToFile(): Uri {
        updateNotification("Loading...")
        val picture = BitmapFactory.decodeStream(this@WS03Service.assets.open(DEFAULT_FILE_NAME))
        updateNotification("Processing...")
        val output = blurBitmap(picture, this@WS03Service)
        updateNotification("Preparing...")
        return writeBitmapToFile(this@WS03Service, output)
    }

    private fun createCoroutineScope() = CoroutineScope(Job() + Dispatchers.IO)

    private suspend fun updateNotification(title: String) {
        val notification = createNotification(this, title)
        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notification)

        delay(1_000)
    }

    companion object {
        private const val NOTIFICATION_ID = 1
        private const val TAG = "WS03Service"

        private const val DEFAULT_FILE_NAME = "test.jpg"
    }
}
