package com.android.fundamentals.workshop01

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

open class WS01BaseActivity : AppCompatActivity() {

    private var am: ActivityManager? = null
    protected var taskLog: String = ""
    protected var stackLog: String = ""
    protected var infoLog: String = ""

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        createActivityManager()
        updateInfo()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        updateInfo()
    }

    override fun onDestroy() {
        super.onDestroy()

        am = null
    }

    private fun updateInfo() {
        taskLog = "taskId:${this.taskId}, isTaskRoot:${this.isTaskRoot}"
    }

    private fun createActivityManager() {
        am = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?
    }
}