package com.android.academy.fundamentals.app.workshop01.solution

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.widget.Toast
import java.util.*

private const val KEY_TIK = "key"
private const val TIMER_DELAY_MS = 300L
private const val TIMER_PERIOD_MS = 3000L

class WS01StartedService : Service() {

    private val timer: Timer = Timer()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(applicationContext, "Services starts", Toast.LENGTH_SHORT).show()
        timer.scheduleAtFixedRate(MainTask(), TIMER_DELAY_MS, TIMER_PERIOD_MS)

        return START_NOT_STICKY
    }

    inner class MainTask : TimerTask() {

        override fun run() {
            val mes = Message.obtain()
            mes.data.putString(KEY_TIK, "Tik")

            toastHandler.sendMessage(mes)
        }

        override fun cancel(): Boolean {
            val mes = Message.obtain()
            mes.data.putString(KEY_TIK, "Time off")

            toastHandler.sendMessage(mes)

            return super.cancel()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        timer.cancel()
        Toast.makeText(applicationContext, "Service stopped", Toast.LENGTH_SHORT).show()
    }

    private val toastHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            Toast.makeText(applicationContext, msg.data.getString(KEY_TIK), Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
