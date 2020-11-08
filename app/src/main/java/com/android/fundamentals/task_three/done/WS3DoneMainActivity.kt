package com.android.fundamentals.task_three.done

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R

class WS3DoneMainActivity : AppCompatActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_ws3_done)
    }
}