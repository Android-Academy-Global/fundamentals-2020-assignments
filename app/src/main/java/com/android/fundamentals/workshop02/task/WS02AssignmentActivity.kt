package com.android.fundamentals.workshop02.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R
import com.android.fundamentals.workshop02.WS02RootFragment

class WS02AssignmentActivity : AppCompatActivity(), WS02RootFragment.TransactionsFragmentClicks {

    private val rootFragment =
        WS02RootFragment().apply { setClickListener(this@WS02AssignmentActivity) }
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws02_ws03)

        supportFragmentManager.beginTransaction()
            .apply {
                add(R.id.persistent_container, rootFragment)
                commit()
            }
    }


    override fun addRedFragment() {
        count++
        //TODO(WS3:1) Add red fragment like SecondFragmentWS3.newInstance(countId, R.color.red)
    }

    override fun addBlueFragment() {
        count++
        //TODO(WS3:2) Add blue fragment like SecondFragmentWS3.newInstance(countId, R.color.blue)
    }

    override fun removeLast() {
        if (supportFragmentManager.fragments.size > 1) {
            count--
            //TODO(WS3:3) Remove fragment
        }
    }

    override fun replaceFragment() {
        count = 1
        //TODO(WS3:4) Replace current fragment green fragment SecondFragmentWS3.newInstance(countId, R.color.green)
    }

}