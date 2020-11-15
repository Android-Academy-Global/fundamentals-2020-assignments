package com.android.fundamentals.workshop01.done

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R

class WS01Step02SolutionDefaultMainActivity : AppCompatActivity() {

    private var btnIncrementer: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "$LOG_PREFIX::onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws01_step01)

        setupUi()
    }

    override fun onDestroy() {
        Log.d(TAG, "$LOG_PREFIX::onDestroy")
        super.onDestroy()
    }

    private fun setupUi() {
        btnIncrementer = findViewById<Button>(R.id.btn_incrementer).apply {
            setOnClickListener {

            }
        }
    }
}

private const val TAG = "WS01Step02SolutionDefaultMainActivity"
private const val LOG_PREFIX = "WS01ST02"