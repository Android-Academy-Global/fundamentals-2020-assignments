package com.android.academy.fundamentals.ws4

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.R

/*
* There are 4 TODOs in this workshop
* For TODO 01 change inside fragment_splash layout app:layoutDescription="@xml/ws04_animation_todo_01"
* For TODO 02 change inside fragment_splash layout app:layoutDescription="@xml/ws04_animation_todo_02"
* For TODO 03 change inside fragment_splash layout app:layoutDescription="@xml/ws04_animation_todo_03"
* For TODO 01 change inside fragment_splash layout app:layoutDescription="@xml/ws04_animation_todo_04"
*/

class WS04Fragment : Fragment(R.layout.fragment_splash) {

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