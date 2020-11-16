package com.android.fundamentals.workshop01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.android.fundamentals.R

class WS01Step03OuterActivity : AppCompatActivity() {

    private var btnStartA: AppCompatButton? = null
    private var btnStartB: AppCompatButton? = null
    private var btnStartC: AppCompatButton? = null
    private var btnStartD: AppCompatButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "$LOG_PREFIX::onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws01_step03)

        setupUi()
    }

    override fun onDestroy() {
        Log.d(TAG, "$LOG_PREFIX::onDestroy")
        super.onDestroy()
    }

    private fun setupUi() {
        btnStartA = findViewById<AppCompatButton>(R.id.btn_start_a).apply {
            setOnClickListener {
                routeTo(WS01Step02ActivityA::class.java)
            }
        }
        btnStartB = findViewById<AppCompatButton>(R.id.btn_start_b).apply {
            setOnClickListener {
                routeTo(WS01Step02ActivityB::class.java)
            }
        }
        btnStartC = findViewById<AppCompatButton>(R.id.btn_start_c).apply {
            setOnClickListener {
                routeTo(WS01Step02ActivityC::class.java)
            }
        }
        btnStartD = findViewById<AppCompatButton>(R.id.btn_start_d).apply {
            setOnClickListener {
                routeTo(WS01Step02ActivityD::class.java)
            }
        }
    }

    private fun routeTo(cls: Class<out AppCompatActivity>) {
        startActivity(Intent(this, cls))
    }
}

private const val TAG = "WS01Step03OuterActivity"
private const val LOG_PREFIX = "WS01ST03"