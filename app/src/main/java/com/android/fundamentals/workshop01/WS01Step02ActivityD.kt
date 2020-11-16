package com.android.fundamentals.workshop01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import com.android.fundamentals.R

class WS01Step02ActivityD : WS01BaseActivity() {

    private var btnDStartA: AppCompatButton? = null
    private var btnDStartB: AppCompatButton? = null
    private var btnDStartC: AppCompatButton? = null
    private var btnDStartD: AppCompatButton? = null

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
        btnDStartA = findViewById<AppCompatButton>(R.id.btn_d_start_a).apply {
            setOnClickListener {
                routeTo(WS01Step02ActivityA::class.java)
            }
        }
        btnDStartB = findViewById<AppCompatButton>(R.id.btn_d_start_b).apply {
            setOnClickListener {
                routeTo(WS01Step02ActivityB::class.java)
            }
        }
        btnDStartC = findViewById<AppCompatButton>(R.id.btn_d_start_c).apply {
            setOnClickListener {
                routeTo(WS01Step02ActivityC::class.java)
            }
        }
        btnDStartD = findViewById<AppCompatButton>(R.id.btn_d_start_d).apply {
            setOnClickListener {
                routeTo(WS01Step02ActivityD::class.java)
            }
        }
    }

    private fun printInfo() {
        val info = """
            | $LOG_PREFIX::printInfo
            | $taskLog
            | $stackLog
            | $infoLog
        """.trimIndent()
        Log.d(TAG, info)
    }
}

private const val TAG = "WS01Step02ActivityD"
private const val LOG_PREFIX = "WS01ST02"