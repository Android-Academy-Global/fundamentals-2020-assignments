package com.android.academy.fundamentals.app.workshop04

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.app.R

class WS04Fragment : Fragment(R.layout.fragment_ws04) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Todo 4.4: Enqueue simpleRequest with WorkManager
        //Todo 4.6: Enqueue delayedRequest with WorkManager
        //Todo 4.8: Enqueue constrainedRequest with WorkManager
    }
    
    companion object {
        fun create() = WS04Fragment()
    }
}
