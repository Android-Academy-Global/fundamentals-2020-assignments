package com.android.academy.fundamentals

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.app.R
import com.bumptech.glide.Glide

class WS03Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ws03, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = requireContext()

        view.findViewById<Button>(R.id.start_service)?.apply {
            setOnClickListener {
                // start service + PendingIntent to receive answer
                // restarting
                // stopping service with another start intent

                val startServiceIntent = Intent(context, WS03Service::class.java)
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    context.startForegroundService(startServiceIntent)
                } else {
                    // not supported for API < 26
                    context.startService(startServiceIntent)
                }
            }
        }

        view.findViewById<Button>(R.id.stop_service)?.apply {
            setOnClickListener {
                val intent = Intent(context, WS03Service::class.java)
                context.stopService(intent)
            }
        }

        view.findViewById<ImageView>(R.id.image)?.apply {
            Glide
                .with(requireContext())
                .load(Uri.parse(DEFAULT_IMAGE_URI))
                .into(this)
        }
    }

    companion object {
        private const val DEFAULT_IMAGE_URI = "file:///android_asset/test.jpg"
    }
}
