package com.android.academy.fundamentals.app.workshop01.solution

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.app.R

class WS01SolutionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_ws01, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.start_started_service).setOnClickListener {
            val intent = Intent(context, WS01StartedService::class.java)
            context?.startService(intent)
        }
        view.findViewById<View>(R.id.stop_started_service)?.setOnClickListener {
            context?.stopService(Intent(context, WS01StartedService::class.java))
        }
    }

    companion object {
        fun create() = WS01SolutionFragment()
    }
}
