package com.android.fundamentals.workshop03.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R
import com.android.fundamentals.workshop03.WS03RootFragment

class WS03AssignmentActivity : AppCompatActivity(), WS03RootFragment.TransactionsFragmentClicks {

    private val rootFragment =
        WS03RootFragment().apply { setClickListener(this@WS03AssignmentActivity) }
    private var count: Int = 0
    private var addBackStack: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws02_ws03)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.persistent_container, rootFragment)
            commit()
        }
    }


    override fun addToBackStack(value: Boolean) {
        //TODO(WS3:5) add addToBackStack check
    }


    override fun addRedFragment() {
        count++
        //TODO(WS3:1) Add red fragment like SecondFragmentWS3.newInstance(countId, R.color.red)
        //TODO(WS3:5) add addToBackStack check
    }


    override fun addBlueFragment() {
        count++
        //TODO(WS3:2) Add blue fragment like SecondFragmentWS3.newInstance(countId, R.color.red)
        //TODO(WS3:5) add addToBackStack check
    }


    override fun removeLast() {
        if (supportFragmentManager.fragments.size > 1) {
            //TODO(WS3:3) Remove fragment
            //TODO(WS3:5) add addToBackStack check
        }
    }


    override fun replaceFragment() {
        count++
        //TODO(WS3:4) Replace current fragment green fragment SecondFragmentWS3.newInstance(countId, R.color.green)
        //TODO(WS3:5) add addToBackStack check
    }


}