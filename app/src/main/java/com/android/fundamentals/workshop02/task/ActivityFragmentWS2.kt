package com.android.fundamentals.workshop02.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R
import com.android.fundamentals.workshop02.SecondFragmentWS2

//TODO(W2:9) Implement interface in Activity
class ActivityFragmentWS2 : AppCompatActivity() {

    //TODO(W2:9) Create root fragment and set listener
    private val secondFragment = SecondFragmentWS2()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_comunications)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager
            .beginTransaction()


        fragmentTransaction.apply {
            //TODO(W2:3) Add the Fragment to the R.id.persistent_container FrameLayout
            //add(R.id.persistent_container, rootFragment)
            add(R.id.fragments_container, secondFragment)
            commit()
        }
    }

    //TODO(W2:10) Change the text in secondFragment
    //TODO(W2:11) Change fragment text background in secondFragment

}