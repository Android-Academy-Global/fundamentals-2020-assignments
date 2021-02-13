package com.android.academy.fundamentals.workshop2.solution

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.view.View
import androidx.core.animation.addListener
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.R

class WS02SolutionFragment : Fragment(R.layout.fragment_ws02) {

    private var isFirstStart: Boolean = true

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
        if (isFirstStart) {
            isFirstStart = false
            startAnimations()
        }
    }

    private fun startAnimations() {

        val rocketPrepareAnimator = AnimatorInflater.loadAnimator(requireContext(), R.animator.prepare_rocket)
        rocketPrepareAnimator.setTarget(rocket)

        val fireAnimator = AnimatorInflater.loadAnimator(requireContext(), R.animator.fire)
        fireAnimator.setTarget(fire)

        rocketPrepareAndFireAnimatorSet.playSequentially(rocketPrepareAnimator, fireAnimator)

        rocketPrepareAndFireAnimatorSet.addListener(onEnd = {
            startRocketFlyAnimation()
        })

        rocketPrepareAndFireAnimatorSet.start()
    }

    private fun startRocketFlyAnimation(){
        rocket.animate()
            .x(0f)
            .y(0f)
            .setDuration(5000)
            .start()

        fire.animate()
            .x((rocket.width - fire.width) / 2f)
            .y(rocket.height.toFloat())
            .setDuration(5000)
            .start()

        rocket.animate()
            .alpha(0f)
            .setStartDelay(4000)
            .setDuration(1000)
            .start()

        fire.animate()
            .alpha(0f)
            .setStartDelay(4000)
            .setDuration(1000)
            .start()
    }

    override fun onDestroy() {
        rocketPrepareAndFireAnimatorSet.removeAllListeners()
        rocketPrepareAndFireAnimatorSet.cancel()

        rocket.clearAnimation()
        fire.clearAnimation()
        super.onDestroy()
    }
}