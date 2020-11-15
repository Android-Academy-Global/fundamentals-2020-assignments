package com.android.fundamentals.workshop01.task

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R

class WS01Step01AssignmentActivity : AppCompatActivity() {

    // This is a click counter
    private var counter = 0

    // These are views
    private var tvValue: TextView? = null
    private var btnIncrementer: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        // Logging onCreate()
        Log.d(TAG, "$LOG_PREFIX::onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws01_step01)

        // Find view in layout by id and init view property
        tvValue = findViewById<TextView>(R.id.tv_value).apply {
            // Set initial text
            text = "The value is: $counter"
        }

        // Find view in layout by id and init button property
        btnIncrementer = findViewById<Button>(R.id.btn_incrementer).apply {
            // Set click listener
            setOnClickListener {
                // It will increment click counter
                counter++
                // And update text value on screen
                tvValue?.text = "The value is $counter"
            }
        }
    }

    //TODO(WS01:ST01:01) Sync and Build project, start emulator, run app.
    // In the existed app, click several times on a button. Rotate the emulator's screen and apply rotation.
    // Check that the counter resets to the initial value.

    //TODO(WS01:ST01:02) Override Save Instance State and Restore Instance State functions.

    //TODO(WS01:ST01:03) Inside the Save Instant State function,
    // put the Int "counter" value into the "outState" bundle, use "KEY_COUNT_ARGUMENT" as a key.

    //TODO(WS01:ST01:04) Inside the Restore Instant State function,
    // get the Int "counter" value from the "savedState" bundle, use "KEY_COUNT_ARGUMENT" as a key.
    // Update text value on screen.

    override fun onDestroy() {
        // Logging onDestroy()
        Log.d(TAG, "${LOG_PREFIX}::onDestroy")
        super.onDestroy()
    }
}

private const val KEY_COUNT_ARGUMENT = "KEY_COUNT_ARGUMENT"
private const val TAG = "WS01Step01SolutionActivity"
private const val LOG_PREFIX = "WS01ST01"