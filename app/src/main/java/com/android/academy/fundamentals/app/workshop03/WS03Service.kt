package com.android.academy.fundamentals

import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.android.academy.fundamentals.app.R
import com.android.academy.fundamentals.app.blurBitmap
import com.android.academy.fundamentals.app.workshop03.WS03NotificationManager
import com.android.academy.fundamentals.app.writeBitmapToFile
import kotlinx.coroutines.*

private const val TAG = "WS03Service"
private const val DEFAULT_FILE_NAME = "test.jpg"

//TODO 01: Create new class which extends Service()
class WS03Service {

    private val internalNotificationManager = WS03NotificationManager()

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
//            val notification = internalNotificationManager.createNotificationForOreo(
//                this,
//                getString(R.string.ws03_service_title)
//            )
//
//            //TODO 04: Call startForeground, pass WS03NotificationManager.NOTIFICATION_ID and notification
//        }
//    }

    //TODO 05: Uncomment startJob method
//    private fun startJob() {
//        if (payloadJob?.isActive == true) {
//            payloadJob?.cancel()
//        }
//        payloadJob = coroutineScope.launch(context = exceptionHandler) {
//            val resultFileUri = blurAndSaveToFile()
//
//            internalNotificationManager.createNotificationAfterJobDone(
//                this@WS03Service,
//                resultFileUri
//            )
//        }
//    }

    //TODO 06: Implement onStartCommand callback to start new job. Method should return START_NOT_STICKY
    // startJob()

    // TODO 07: Implement onDestroy callback to cancel coroutines
    // coroutineScope.cancel("It's time")

    //TODO 05: Uncomment blurAndSaveToFile method which will blur the image and update notification about progress
//    private suspend fun blurAndSaveToFile(): Uri {
//        internalNotificationManager.updateNotification(this, "Loading...")
//        // Just emulates long running task
//        delay(1_000)
//
//        val picture = BitmapFactory.decodeStream(this.assets.open(DEFAULT_FILE_NAME))
//        internalNotificationManager.updateNotification(this, "Processing...")
//        // Just emulates long running task
//        delay(1_000)
//
//        val output = blurBitmap(picture, this)
//        internalNotificationManager.updateNotification(this, "Preparing...")
//        // Just emulates long running task
//        delay(1_000)
//
//        return writeBitmapToFile(this, output)
//    }

    private fun createCoroutineScope() = CoroutineScope(Job() + Dispatchers.IO)
}
