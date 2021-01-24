package com.android.academy.fundamentals.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.academy.fundamentals.app.workshop01.WS01Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WS01Fragment.create())
                .commit()
        }
    }
}
