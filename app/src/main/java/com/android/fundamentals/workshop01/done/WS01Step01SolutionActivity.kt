package com.android.fundamentals.workshop01.done

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R

class WS01Step01SolutionActivity : AppCompatActivity() {

    private var counter = 0

    private var tvValue: TextView? = null
    private var btnIncrementer: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "$LOG_PREFIX::onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws01_step01)

        setupUi()
        savedInstanceState.ifNull {
            updateData(counter)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG, "$LOG_PREFIX::onSaveInstanceState")
        super.onSaveInstanceState(outState)

        outState.putInt(KEY_COUNT_ARGUMENT, counter)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d(TAG, "$LOG_PREFIX::onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)

        counter = savedInstanceState.getInt(KEY_COUNT_ARGUMENT) // getInt() provides "0" by default
        updateData(counter)
    }

    override fun onDestroy() {
        Log.d(TAG, "$LOG_PREFIX::onDestroy")
        super.onDestroy()
    }

    private fun setupUi() {
        tvValue = findViewById(R.id.tv_value)

        btnIncrementer = findViewById<Button>(R.id.btn_incrementer).apply {
            setOnClickListener {
                counter++
                updateData(counter)
            }
        }
    }

    private fun updateData(counter: Int) {
        tvValue?.text = "The value is: $counter"
    }
}

private fun <T> T?.ifNull(block: () -> Unit) {
    if (this == null) {
        block()
    }
}

private const val KEY_COUNT_ARGUMENT = "KEY_COUNT_ARGUMENT"
private const val TAG = "WS01Step01SolutionActivity"
private const val LOG_PREFIX = "WS01ST01"