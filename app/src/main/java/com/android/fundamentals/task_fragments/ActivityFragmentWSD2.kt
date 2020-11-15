package com.android.fundamentals.task_fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R

//TODO(W2:9) Implement interface in Activity
class ActivityFragmentWSD2 : AppCompatActivity(), RootFragmentWSD2.ClickListener {

    private val rootFragment = RootFragmentWSD2().apply { setListener(this@ActivityFragmentWSD2) }
    private val secondFragment = SecondFragmentWS2()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_comunications)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager
            .beginTransaction()


        fragmentTransaction.apply {
            //TODO(W2:3) Add the Fragment to the FrameLayout
            add(R.id.persistent_container, rootFragment)
            add(R.id.fragments_container, secondFragment)
            commit()
        }
    }

    //TODO(W2:10) Change the text in secondFragment
    override fun increaseValue() {
        secondFragment.increaseValue()
    }

    //TODO(W2:11) Change fragment text background in secondFragment
    override fun changeBackground() {
        secondFragment.changeBackground()
    }
}