package com.android.fundamentals.workshop01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import com.android.fundamentals.R

class WS01Step02ActivityA : WS01BaseActivity() {

    private var btnAStartB: AppCompatButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "$LOG_PREFIX::onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws01_step02_a)

        setupUi()
    }

    override fun onNewIntent(intent: Intent?) {
        Log.d(TAG, "$LOG_PREFIX::onNewIntent")
        super.onNewIntent(intent)
    }

    override fun onResume() {
        super.onResume()
        printInfo()
    }

    override fun onDestroy() {
        Log.d(TAG, "$LOG_PREFIX::onDestroy")
        super.onDestroy()
    }

    private fun setupUi() {
        btnAStartB = findViewById<AppCompatButton>(R.id.btn_a_start_b).apply {
            setOnClickListener {
                routeTo(WS01Step02ActivityB::class.java)
            }
        }
    }

    private fun printInfo() {
        val info = """
            | $LOG_PREFIX::printInfo
            | $taskLog
        """.trimIndent()
        Log.d(TAG, info)
    }
}

private const val TAG = "WS01Step02ActivityA"
private const val LOG_PREFIX = "WS01ST02"