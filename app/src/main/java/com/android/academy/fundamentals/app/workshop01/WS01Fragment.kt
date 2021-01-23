package com.android.academy.fundamentals.app.workshop01

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.app.R

class WS01Fragment: Fragment() {
    private var startServiceButton: Button? = null
    private var stopServiceButton: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_ws01, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews(view)
        setupListeners()
    }

    override fun onDestroyView() {
        clearViews()
        super.onDestroyView()
    }

    private fun setupViews(parent: View) {
        startServiceButton = parent.findViewById(R.id.start_started_service)
        stopServiceButton = parent.findViewById(R.id.stop_started_service)
    }

    private fun setupListeners() {
        startServiceButton?.setOnClickListener {
            //TODO 06 : start service
            // create intent
            // Intent(context, WS01StartedService::class.java)
            // put it into method context?.startService(intent)

        }
        stopServiceButton?.setOnClickListener {
            //TODO 07 : stop service
            // create intent
            // Intent(context, WS01StartedService::class.java)
            // put it into method context?.stopService(intent)
        }
    }

    private fun clearViews() {
    }

    companion object {
        fun create() = WS01Fragment()
    }
}