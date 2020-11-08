package com.android.fundamentals.task_two

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R

class WS2TaskSecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws2_second)

        findViewById<Button>(R.id.btn_first)?.apply {
            setOnClickListener {
                startActivity(Intent(this@WS2TaskSecondActivity, WS2TaskFirstActivity::class.java))
            }
        }

        findViewById<Button>(R.id.btn_second)?.apply {
            setOnClickListener {
                startActivity(Intent(this@WS2TaskSecondActivity, WS2TaskSecondActivity::class.java))
            }
        }

        findViewById<Button>(R.id.btn_three)?.apply {
            setOnClickListener {
                startActivity(Intent(this@WS2TaskSecondActivity, WS2TaskThirdActivity::class.java))
            }
        }

        findViewById<Button>(R.id.btn_fourth)?.apply {
            setOnClickListener {
                startActivity(Intent(this@WS2TaskSecondActivity, WS2TaskFourthActivity::class.java))
            }
        }
    }

}