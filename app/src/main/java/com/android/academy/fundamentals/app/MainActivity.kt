package com.android.academy.fundamentals.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.academy.fundamentals.WS03Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(android.R.id.content, WS03Fragment())
                .commit()
        }
    }
}
