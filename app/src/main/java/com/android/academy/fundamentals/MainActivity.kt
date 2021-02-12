package com.android.academy.fundamentals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.academy.fundamentals.ws4.SplashFragment
import com.android.academy.fundamentals.ws4.solution.FinalSplashFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, FinalSplashFragment())
                .commit()
        }
    }
}