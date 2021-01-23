package com.android.academy.fundamentals.app.workshop01.solution

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.app.R

class WS01SolutionFragment: Fragment() {

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
            val intent = Intent(context, WS01StartedService::class.java)
            context?.startService(intent)
        }
        stopServiceButton?.setOnClickListener {
           context?.stopService(Intent(context, WS01StartedService::class.java))
        }
    }

    private fun clearViews() {
    }

    companion object {
        fun create() = WS01SolutionFragment()
    }
}