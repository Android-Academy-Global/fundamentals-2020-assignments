package com.android.fundamentals.workshop01.step02

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.android.fundamentals.R

class WS01Step02ActivityD : AppCompatActivity() {

    private var btnStartA: AppCompatButton? = null
    private var btnStartB: AppCompatButton? = null
    private var btnStartC: AppCompatButton? = null
    private var btnStartD: AppCompatButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "$LOG_PREFIX::onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws01_step02_d)

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
        btnStartA = findViewById<AppCompatButton>(R.id.btn_start_a).apply {
            setOnClickListener {
                startActivity(Intent(this@WS01Step02ActivityD, WS01Step02ActivityA::class.java))
            }
        }
        btnStartB = findViewById<AppCompatButton>(R.id.btn_start_b).apply {
            setOnClickListener {
                startActivity(Intent(this@WS01Step02ActivityD, WS01Step02ActivityB::class.java))
            }
        }
        btnStartC = findViewById<AppCompatButton>(R.id.btn_start_c).apply {
            setOnClickListener {
                startActivity(Intent(this@WS01Step02ActivityD, WS01Step02ActivityC::class.java))
            }
        }
        btnStartD = findViewById<AppCompatButton>(R.id.btn_start_d).apply {
            setOnClickListener {
                startActivity(Intent(this@WS01Step02ActivityD, WS01Step02ActivityD::class.java))
            }
        }
    }

    // For some reason, taskId and isTaskRoot may not appear in during standard launch mode.
    private fun printInfo() {
        val info = """
            | $LOG_PREFIX::printInfo
            | taskId:${this.taskId}, isTaskRoot:${this.isTaskRoot}
        """.trimIndent()
        Log.d(TAG, info)
    }

    companion object {
        private const val TAG = "WS01Step02ActivityD"
        private const val LOG_PREFIX = "WS01ST02"
    }
}

