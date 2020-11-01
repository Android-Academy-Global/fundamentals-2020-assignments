package com.android.academy.fundamentals.solutions

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.academy.fundamentals.R

class SecondActivity_Solution : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val transmittedString = intent.getStringExtra(TRANSMITTED_STRING)
        val transmittedInt = intent.getIntExtra(TRANSMITTED_INT, -1)
        val transmittedBoolean = intent.getBooleanExtra(TRANSMITTED_BOOLEAN, false)

        val textView: TextView = findViewById(R.id.second_activity_text_view)
        textView.text = "transmittedString $transmittedString, transmittedInt $transmittedInt, transmittedBoolean $transmittedBoolean"
    }

    companion object {
        const val TRANSMITTED_STRING = "transmittedString"
        const val TRANSMITTED_INT = "transmittedInt"
        const val TRANSMITTED_BOOLEAN = "transmittedBoolean"
    }
}
