package com.android.fundamentals.task_fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.android.fundamentals.R

class ActivityFragmentWSD3 : AppCompatActivity(), RootFragmentWS3.TransactionsFragmentClicks {

    private val rootFragment = RootFragmentWS3().apply { setClickListener(this@ActivityFragmentWSD3) }
    private val fragmentManager = supportFragmentManager
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
        fragmentManager.beginTransaction().apply {
            add(R.id.fragments_container ,SecondFragmentWS3.newInstance(countId, R.color.red))
            if(addBackStack) addToBackStack(null)
            commit()
        }
    }

    //TODO(W3:2) Add blue fragment like SecondFragmentWS3.newInstance(countId, R.color.red)
    //TODO(W3:5) add addToBackStack check
    override fun addBlueFragment() {
        countId++
        fragmentManager.beginTransaction().apply {
            add(R.id.fragments_container ,SecondFragmentWS3.newInstance(countId, R.color.blue))
            if(addBackStack) addToBackStack(null)
            commit()
        }
    }

    //TODO(W3:3) Remove fragment
    //TODO(W3:5) add addToBackStack check
    override fun removeLast() {
        if (fragmentManager.fragments.size > 1) {
            val lastFragment = fragmentManager.fragments.last()
            fragmentManager.beginTransaction().apply {
                remove(lastFragment)
                if(addBackStack) addToBackStack(null)
                commit()
            }
        }
    }

    //TODO(W3:4) Replace current fragment green fragment SecondFragmentWS3.newInstance(countId, R.color.green)
    //TODO(W3:5) add addToBackStack check
    override fun replaceFragment() {
        countId++
        fragmentManager.beginTransaction().apply {
            replace(R.id.fragments_container ,SecondFragmentWS3.newInstance(countId, R.color.green))
            if(addBackStack) addToBackStack(null)
            commit()
        }
    }


}