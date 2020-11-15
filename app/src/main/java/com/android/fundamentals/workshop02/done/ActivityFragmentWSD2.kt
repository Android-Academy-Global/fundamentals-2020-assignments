package com.android.fundamentals.workshop02.done

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R
import com.android.fundamentals.workshop02.SecondFragmentWS2


class ActivityFragmentWSD2 : AppCompatActivity(), RootFragmentWSD2.ClickListener {

    private val rootFragment = RootFragmentWSD2().apply { setListener(this@ActivityFragmentWSD2) }
    private val secondFragment = SecondFragmentWS2()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_comunications)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.persistent_container, rootFragment)
            add(R.id.fragments_container, secondFragment)
            commit()
        }
    }

    override fun increaseValue() {
        secondFragment.increaseValue()
    }

    override fun changeBackground() {
        secondFragment.changeBackground()
    }
}