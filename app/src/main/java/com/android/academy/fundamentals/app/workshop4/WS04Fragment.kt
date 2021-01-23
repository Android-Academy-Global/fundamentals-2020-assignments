package com.android.academy.fundamentals.app.workshop4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.work.WorkManager
import com.android.academy.fundamentals.WS03Service
import com.android.academy.fundamentals.app.R
import com.bumptech.glide.Glide

class WS04Fragment : Fragment(R.layout.fragment_ws04) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Todo 4.4: Enqueue simpleRequest with WorkManager
        //Todo 4.6: Enqueue delayedRequest with WorkManager
        //Todo 4.8: Enqueue constrainedRequest with WorkManager
    }
}
