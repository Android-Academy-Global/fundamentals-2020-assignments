package com.android.fundamentals.workshop01.step01.done

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.android.fundamentals.R

class WS01Step01SolutionActivity : AppCompatActivity() {

    private var counter = 0

    private var tvValue: AppCompatTextView? = null
    private var btnIncrementer: AppCompatButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "$LOG_PREFIX::onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws01_step01)

        setupUi()
        if (savedInstanceState == null) {
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

        btnIncrementer = findViewById<AppCompatButton>(R.id.btn_incrementer).apply {
            setOnClickListener {
                counter++
                updateData(counter)
            }
        }
    }

    private fun updateData(value: Int) {
        tvValue?.text = getString(R.string.ws01_step01_activity_counter_text, value)
    }

    companion object {
        private const val KEY_COUNT_ARGUMENT = "KEY_COUNT_ARGUMENT"
        private const val TAG = "WS01Step01Activity"
        private const val LOG_PREFIX = "WS01ST01"
    }
}
