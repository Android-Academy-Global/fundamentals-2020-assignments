package com.android.fundamentals.task_two

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R

class WS2TaskThirdActivity : AppCompatActivity() {

    private var tvValue: TextView? = null
    private var btnNext: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws2_third)

        findViewById<Button>(R.id.btn_first)?.apply {
            setOnClickListener {
                startActivity(Intent(this@WS2TaskThirdActivity, WS2TaskFirstActivity::class.java))
            }
        }

        findViewById<Button>(R.id.btn_second)?.apply {
            setOnClickListener {
                startActivity(Intent(this@WS2TaskThirdActivity, WS2TaskSecondActivity::class.java))
            }
        }

        findViewById<Button>(R.id.btn_three)?.apply {
            setOnClickListener {
                startActivity(Intent(this@WS2TaskThirdActivity, WS2TaskThirdActivity::class.java))
            }
        }

        findViewById<Button>(R.id.btn_fourth)?.apply {
            setOnClickListener {
                startActivity(Intent(this@WS2TaskThirdActivity, WS2TaskFourthActivity::class.java))
            }
        }
    }

}