package com.android.fundamentals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.workshop04.WS04DiffUtilsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, WS04DiffUtilsFragment.newInstance())
                    .commit()
        }
    }
}