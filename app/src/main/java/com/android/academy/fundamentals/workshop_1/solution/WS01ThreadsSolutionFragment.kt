package com.android.academy.fundamentals.workshop_1.solution

import android.os.*
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.R

class WS01ThreadsSolutionFragment: Fragment(R.layout.fragment_theads) {

    private var threadButton : Button? = null
    private var threadTextView : TextView? = null

    private val handler = MyHandler()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        findViews(view)
        threadButton?.setOnClickListener {
            startThread()
            startRunnable()
        }
    }

    override fun onDestroyView() {
        threadButton = null
        threadTextView = null
        super.onDestroyView()
    }

    private fun findViews(view: View) {
        threadButton = view.findViewById(R.id.thread_button)
        threadTextView = view.findViewById(R.id.thread_text_view)
    }

    private fun startThread() {
        val thread = MyThread()
        thread.start()
        printMessage(getString(R.string.wait))
    }

    private fun startRunnable() {
        val thread = Thread(MyRunnable())
        thread.start()
        printMessage(getString(R.string.wait))
    }

    private fun printMessage(mes: String){
        threadTextView?.text =  mes
    }

    inner class MyThread : Thread() {

        override fun run() {
            sleep(6000)
            val mes = Message()
            mes.data.putString(MESSAGE_KEY, getString(R.string.thread_worked))
            handler.sendMessage(mes)
        }

    }

    inner class MyRunnable : Runnable {

        override fun run() {
            Thread.sleep(4000)
            val mes = Message()
            mes.data.putString(MESSAGE_KEY, getString(R.string.runnable_worked))
            handler.sendMessage(mes)
        }
    }

    inner class MyHandler : Handler(Looper.getMainLooper()) {

        override fun handleMessage(msg: Message) {
            printMessage(msg.data.getString(MESSAGE_KEY, ""))
        }
    }

    companion object {
        private const val MESSAGE_KEY = "key"
    }
}