package com.android.academy.fundamentals

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import com.android.academy.fundamentals.app.*
import com.android.academy.fundamentals.app.workshop03.WS03ResultActivity
import com.android.academy.fundamentals.app.workshop03.WS03ResultActivity.Companion.KEY_IMAGE_URI
import kotlinx.coroutines.*

//TODO 01: Create new class which extends Service()
class WS03Service {
    private var coroutineScope: CoroutineScope = createCoroutineScope()
    private var payloadJob: Job? = null

    private val exceptionHandler: CoroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { _, throwable ->
            Log.e(TAG, "Coroutine exception, scope active:${coroutineScope.isActive}", throwable)
            coroutineScope = createCoroutineScope()
        }
    }

    // We don't provide binding, so return null
    //TODO 02: Uncomment onBind callback
//    override fun onBind(p0: Intent?): IBinder? = null

    //TODO 03: Uncomment onCreate callback
//    override fun onCreate() {
//        super.onCreate()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(createChannel(this))
//
//            val notification = createNotification(this, getString(R.string.ws03_service_title))
//            //TODO 04: Call startForeground, pass NOTIFICATION_ID and notification
//        }
//    }

    //TODO 05: Uncomment startJob method
//    private fun startJob() {
//        if (payloadJob?.isActive == true) {
//            payloadJob?.cancel()
//        }
//        payloadJob = coroutineScope.launch(context = exceptionHandler) {
//            updateNotification(this@WS03Service, "Loading...")
//            val picture = BitmapFactory.decodeStream(this@WS03Service.assets.open(DEFAULT_FILE_NAME))
//            updateNotification(this@WS03Service, "Processing...")
//            val output = blurBitmap(picture, this@WS03Service)
//            updateNotification(this@WS03Service, "Preparing...")
//            val resultFileUri = writeBitmapToFile(this@WS03Service, output)
//
//            val notifyIntent = Intent(this@WS03Service, WS03ResultActivity::class.java).also {
//                it.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                it.putExtra(KEY_IMAGE_URI, resultFileUri)
//            }
//
//            val notifyPendingIntent = PendingIntent.getActivity(
//                this@WS03Service,
//                0,
//                notifyIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT
//            )
//            val createNotification = createNotification(
//                this@WS03Service,
//                "Done",
//                notifyPendingIntent
//            )
//            NotificationManagerCompat.from(this@WS03Service)
//                .notify(NOTIFICATION_ID, createNotification)
//        }
//    }

    //TODO 06: Implement onStartCommand callback to start new job. Method should return START_NOT_STICKY
    // startJob()

    // TODO 07: Implement onDestroy callback to cancel coroutines
    // coroutineScope.cancel("It's time")

    private fun createCoroutineScope() = CoroutineScope(Job() + Dispatchers.IO)

    private suspend fun updateNotification(context: Context, title: String) {
        val notification = createNotification(context, title)
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)

        // Just emulates long running task
        delay(1_000)
    }

    companion object {
        private const val NOTIFICATION_ID = 1
        private const val TAG = "WS03Service"

        private const val DEFAULT_FILE_NAME = "test.jpg"
    }
}
