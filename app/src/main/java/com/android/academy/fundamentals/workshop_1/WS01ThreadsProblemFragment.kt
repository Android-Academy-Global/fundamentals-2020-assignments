package com.android.academy.fundamentals.workshop_1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.R

class WS01ThreadsProblemFragment: Fragment(R.layout.fragment_theads) {

    private var threadButton : Button? = null
    private var threadTextView : TextView? = null

    private val MESSAGE_KEY = "key"

    //TODO 2 Create a private val handler and set yours

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        findViews(view)
        threadButton?.setOnClickListener {
            startThread()
            startRunnable()
        }
    }

    private fun findViews(view: View) {
        threadButton = view.findViewById(R.id.thread_button)
        threadTextView = view.findViewById(R.id.thread_text_view)
    }

    private fun startThread() {
        printMessage(getString(R.string.wait))
        //TODO 5 create your thread and start it
    }

    private fun startRunnable() {
        printMessage(getString(R.string.wait))
        //TODO 8 create your thread and start it
    }

    private fun printMessage(mes: String){
        threadTextView?.text =  mes
    }

    //TODO 1 Create inner class Handler
    // example: MyHandler : Handler(Looper.getMainLooper())

    //TODO 2 override function handleMessage(mes: Message)
    // at the function run printMessage
    // Get string from mes.data.getString(MESSAGE_KEY, "")

    //TODO 3 Create inner class Thread
    // example: MyThread : Thread()
    // override function run

    //TODO 4 at the run function emulate long work with sleep(6000)
    // create Message, put string to the message.data with MESSAGE_KEY
    // send message to the handler handler.sendMessage(mes)

    //TODO 6 Create inner class Runnable
    // example:  MyRunnable : Runnable
    // override function run

    //TODO 7 at the run function emulate long work with Thread.sleep(4000)
    // create Message, put string to the message.data with MESSAGE_KEY
    // send message to the handler handler.sendMessage(mes)

}