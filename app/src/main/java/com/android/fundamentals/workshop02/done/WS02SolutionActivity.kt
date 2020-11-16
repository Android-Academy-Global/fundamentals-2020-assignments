package com.android.fundamentals.workshop02.done

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R
import com.android.fundamentals.workshop02.WS02SecondFragment

class WS02SolutionActivity : AppCompatActivity(), WS02SolutionFragment.ClickListener {

    private val rootFragment =
        WS02SolutionFragment().apply { setListener(this@WS02SolutionActivity) }
    private val secondFragment = WS02SecondFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_comunications)

        supportFragmentManager.beginTransaction()
            .apply {
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