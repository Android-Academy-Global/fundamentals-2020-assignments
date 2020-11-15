package com.android.fundamentals.task_one.done

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R

class WS1DoneMainActivity : AppCompatActivity() {

    private var counter = 0

    private var tvValue: TextView? = null
    private var btnIncrementer: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "TEST::onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws1)

        setupUi()
        savedInstanceState.ifNull {
            updateData(counter)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG, "TEST::onSaveInstanceState")
        super.onSaveInstanceState(outState)

        outState.putInt(COUNT_ARGUMENT, counter)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d(TAG, "TEST::onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)

        counter = savedInstanceState.getInt(COUNT_ARGUMENT) // getInt() provides "0" by default
        updateData(counter)
    }

    override fun onDestroy() {
        Log.d(TAG, "TEST::onDestroy")

        tvValue = null
        btnIncrementer = null

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

private const val COUNT_ARGUMENT = "CountArgument"
private const val TAG = "WS1DoneMainActivity"