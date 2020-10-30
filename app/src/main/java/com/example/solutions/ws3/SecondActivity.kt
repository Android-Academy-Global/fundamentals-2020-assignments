package com.example.solutions.ws3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.solutions.R

class SecondActivity : AppCompatActivity() {

    var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val transmittedString = intent.getStringExtra(TRANSMITTED_STRING)

        val transmittedInt = intent.getIntExtra(TRANSMITTED_INT, -1)

        val transmittedBoolean = intent.getBooleanExtra(TRANSMITTED_BOOLEAN, false)

        textView = findViewById(R.id.first_activity_text_view)
        textView?.setText("transmittedString $transmittedString, transmittedInt $transmittedInt, transmittedBoolean $transmittedBoolean")
    }

    companion object {
        const val TRANSMITTED_STRING = "transmittedString"
        const val TRANSMITTED_INT = "transmittedInt"
        const val TRANSMITTED_BOOLEAN = "transmittedBoolean"
    }

}