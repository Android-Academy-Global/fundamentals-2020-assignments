package com.android.fundamentals.workshop03.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R
import com.android.fundamentals.workshop03.WS03SecondFragment

//TODO(WS2:9) Implement interface in Activity
class WS03AssignmentActivity : AppCompatActivity() {

    //TODO(WS2:9) Create root fragment and set listener
    private val secondFragment = WS03SecondFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws02_ws03)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager
            .beginTransaction()


        fragmentTransaction.apply {
            //TODO(WS2:3) Add the Fragment to the R.id.persistent_container FrameLayout
            //add(R.id.persistent_container, rootFragment)
            add(R.id.fragments_container, secondFragment)
            commit()
        }
    }

    //TODO(WS2:10) Change the text in secondFragment
    //TODO(WS2:11) Change fragment text background in secondFragment

}