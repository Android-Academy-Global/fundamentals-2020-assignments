package com.android.fundamentals.task_three

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R

class ActivityFragmentWSD3 : AppCompatActivity(), RootFragmentWS3.TransactionsFragmentClicks {

    private val rootFragment = RootFragmentWS3().apply { setClickListener(this@ActivityFragmentWSD3) }
    private var countId: Int = 0
    private var addBackStack:Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_comunications)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.persistent_container, rootFragment)
            commit()
        }
    }

    //TODO(W3:5) add addToBackStack check
    override fun addToBackStack(value: Boolean) {
        addBackStack = value
    }

    //TODO(W3:1) Add red fragment like SecondFragmentWS3.newInstance(countId, R.color.red)
    //TODO(W3:5) add addToBackStack check
    override fun addRedFragment() {
        countId++
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragments_container, SecondFragmentWS3.newInstance(countId, R.color.red))
            if (addBackStack) addToBackStack(null)
            commit()
        }
    }

    //TODO(W3:2) Add blue fragment like SecondFragmentWS3.newInstance(countId, R.color.red)
    //TODO(W3:5) add addToBackStack check
    override fun addBlueFragment() {
        countId++
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragments_container, SecondFragmentWS3.newInstance(countId, R.color.blue))
            if (addBackStack) addToBackStack(null)
            commit()
        }
    }

    //TODO(W3:3) Remove fragment
    //TODO(W3:5) add addToBackStack check
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

    //TODO(W3:4) Replace current fragment green fragment SecondFragmentWS3.newInstance(countId, R.color.green)
    //TODO(W3:5) add addToBackStack check
    override fun replaceFragment() {
        countId++
        supportFragmentManager.beginTransaction().apply {
            replace(
                R.id.fragments_container,
                SecondFragmentWS3.newInstance(countId, R.color.green)
            )
            if (addBackStack) addToBackStack(null)
            commit()
        }
    }


}