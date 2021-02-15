package com.android.academy.fundamentals.ws4.solution

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.R

class WS04SolutionFragment : Fragment(R.layout.final_fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isTransitionToEnd = false
        val motionLayout = view.findViewById<MotionLayout>(R.id.motion_layout)
        val btnAnimate = view.findViewById<Button>(R.id.btn_animate)
        btnAnimate.setOnClickListener {
            if (!isTransitionToEnd) {
                isTransitionToEnd = true
                motionLayout.transitionToEnd()
            } else {
                isTransitionToEnd = false
                motionLayout.transitionToStart()
            }
        }
    }
}