package com.android.academy.fundamentals.workshop2

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.view.View
import androidx.core.animation.addListener
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.R

class WS02Fragment : Fragment(R.layout.fragment_ws02) {

    private var isFirstOnResumeCall: Boolean = true

    private lateinit var rocket: View
    private lateinit var fire: View

    private val rocketPrepareAndFireAnimatorSet = AnimatorSet()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rocket = view.findViewById(R.id.rocket)
        fire = view.findViewById(R.id.fire)
    }

    override fun onResume() {
        super.onResume()
        if (isFirstOnResumeCall) {
            isFirstOnResumeCall = false
            startAnimations()
        }
    }

    private fun startAnimations() {
        //TODO 01: Inflate animator from R.animator.prepare_rocket.
        // Call static method <loadAnimator> of AnimatorInflater to inflate animator from XML.
        // Assign it to rocketPrepareAnimator local variable.
        // Set rocket as animator target. Use <setTarget> method.

        //TODO 02: Inflate animator from R.animator.fire in the same way
        // and assign it to fireAnimator local variable

        //TODO 03: Call <playSequentially> method of rocketPrepareAndFireStartAnimatorSet
        // and pass rocketPrepareAnimator and fireAnimator to arguments

        //TODO 04 Uncomment 49 - 51 lines
        // In these lines we add listener for rocketPrepareAndFireAnimatorSet
        // and call <startRocketFlyAnimation> method in onEnd(onAnimationEnd) callback

//        rocketPrepareAndFireAnimatorSet.addListener(onEnd = {
//            startRocketFlyAnimation()
//        })

        //TODO 09: Call <start> method of rocketPrepareAndFireAnimatorSet

    }

    private fun startRocketFlyAnimation() {
        //TODO 05: Create ViewPropertyAnimator by calling <animate> method of rocket view.
        // Use <x> method to set 0F
        // Use <y> method to set 0F
        // Use <setDuration> method to set 5000
        // Call <start> method to start animation

        //TODO 06: Create ViewPropertyAnimator by calling <animate> method of fire view.
        // Use <x> method to set (rocket.width - fire.width) / 2F
        // Use <y> method to set rocket.height.toFloat()
        // Use <setDuration> method to set 5000
        // Call <start> method to start animation

        //TODO 07: Create ViewPropertyAnimator by calling <animate> method of rocket view.
        // Use <alpha> method to set 0F
        // Use <setDuration> method to set 1000
        // Use <setStartDelay> method to set 4000
        // Call <start> method to start animation

        //TODO 08: repeat TODO 08 for fire
    }

    override fun onDestroy() {
        //TODO 10: stop all animations
        // call <removeAllListeners> and <cancel> methods of rocketPrepareAndFireAnimatorSet
        // call <clearAnimation> of rocket and fire views

        super.onDestroy()
    }
}