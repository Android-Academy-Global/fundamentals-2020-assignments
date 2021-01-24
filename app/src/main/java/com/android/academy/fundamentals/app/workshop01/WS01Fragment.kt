package com.android.academy.fundamentals.app.workshop01

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.app.R

class WS01Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_ws01, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.start_started_service).setOnClickListener {
            //TODO 06 : start service
            // create intent
            // Intent(context, WS01StartedService::class.java)
            // put it into method context?.startService(intent)
        }
        view.findViewById<View>(R.id.stop_started_service)?.setOnClickListener {
            //TODO 07 : stop service
            // create intent
            // Intent(context, WS01StartedService::class.java)
            // put it into method context?.stopService(intent)
        }
    }

    companion object {
        fun create() = WS01Fragment()
    }
}
