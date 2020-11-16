package com.android.fundamentals.workshop03.done

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R
import com.android.fundamentals.workshop03.WS03RootFragment
import com.android.fundamentals.workshop03.WS03SecondFragment

class WS03SolutionActivity : AppCompatActivity(), WS03RootFragment.TransactionsFragmentClicks {

    private val rootFragment =
        WS03RootFragment().apply { setClickListener(this@WS03SolutionActivity) }
    private var countId: Int = 0
    private var addBackStack: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_comunications)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.persistent_container, rootFragment)
            commit()
        }
    }

    override fun addToBackStack(value: Boolean) {
        addBackStack = value
    }

    override fun addRedFragment() {
        countId++
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragments_container, WS03SecondFragment.newInstance(countId, R.color.red))
            if (addBackStack) addToBackStack(null)
            commit()
        }
    }

    override fun addBlueFragment() {
        countId++
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragments_container, WS03SecondFragment.newInstance(countId, R.color.blue))
            if (addBackStack) addToBackStack(null)
            commit()
        }
    }

    override fun removeLast() {
        if (supportFragmentManager.fragments.size > 1) {
            val lastFragment = supportFragmentManager.fragments.last()
            supportFragmentManager.beginTransaction().apply {
                remove(lastFragment)
                if (addBackStack) addToBackStack(null)
                commit()
            }
        }
    }

    override fun replaceFragment() {
        countId++
        supportFragmentManager.beginTransaction().apply {
            replace(
                R.id.fragments_container,
                WS03SecondFragment.newInstance(countId, R.color.green)
            )
            if (addBackStack) addToBackStack(null)
            commit()
        }
    }

}