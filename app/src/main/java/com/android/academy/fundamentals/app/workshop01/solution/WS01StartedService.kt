package com.android.academy.fundamentals.app.workshop01.solution

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.widget.Toast
import java.util.*


class WS01StartedService: Service() {

    private val timer: Timer = Timer()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(applicationContext, "Services starts", Toast.LENGTH_SHORT).show()
        timer.scheduleAtFixedRate(mainTask(), 300, 3000)
        return START_NOT_STICKY
    }

    inner class mainTask : TimerTask() {

        override fun run() {
            val mes = Message()
            mes.data.putString("key", "Tik")
            toastHandler.sendMessage(mes)
        }

        override fun cancel(): Boolean {
            val mes = Message()
            mes.data.putString("key", "Time off")
            toastHandler.sendMessage(mes)
            return super.cancel()
        }
    }

    override fun onDestroy() {
        timer.cancel()
        Toast.makeText(applicationContext, "Service stopped", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }

    private val toastHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            Toast.makeText(applicationContext, msg.data.getString("key") , Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}