package com.android.fundamentals.task_one.task

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R

class WS1TaskMainActivity : AppCompatActivity() {

    private var count = 0

    private var tvValue: TextView? = null
    private var btnIncrementer: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws1)

        tvValue = findViewById<TextView>(R.id.tv_value).apply {
            text = "The value is: $count"
        }

        btnIncrementer = findViewById<Button>(R.id.btn_incrementer).apply {
            setOnClickListener {
                count++
                tvValue?.text = "The value is $count"
            }
        }
    }

}