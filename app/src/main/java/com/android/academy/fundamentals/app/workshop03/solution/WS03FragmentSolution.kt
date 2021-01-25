package com.android.academy.fundamentals.app.workshop03.solution

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

private const val DEFAULT_IMAGE_URI = "file:///android_asset/test.jpg"

class WS03FragmentSolution : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ws03, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = requireContext()

        view.findViewById<Button>(R.id.start_service)?.apply {
            setOnClickListener {
                val startServiceIntent = Intent(context, WS03ServiceSolution::class.java)

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    //TODO 10: Call context.startForegroundService and pass startServiceIntent
                    context.startForegroundService(startServiceIntent)
                } else {
                    //TODO 11: Call context.startService and pass startServiceIntent
                    context.startService(startServiceIntent)
                }
            }
        }

        view.findViewById<Button>(R.id.stop_service)?.apply {
            setOnClickListener {
                val intent = Intent(context, WS03ServiceSolution::class.java)
                //TODO 12: Call context.stopService and pass intent
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
        fun create() = WS03FragmentSolution()
    }
}
