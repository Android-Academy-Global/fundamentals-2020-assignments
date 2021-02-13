package com.android.academy.fundamentals.workshops.solution

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.CycleInterpolator
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.R

class WS01SolutionFragment : Fragment(R.layout.fragment_ws01) {

    companion object {

        private const val DURATION = 2000L
    }

    private lateinit var image: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image = view.findViewById(R.id.image)

        view.findViewById<View>(R.id.button_rotation).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(360F)
            animation(image, valueAnimator)
        }

        view.findViewById<View>(R.id.button_rotation_x).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(360F)
            animation(image, valueAnimator)
        }

        view.findViewById<View>(R.id.button_rotation_y).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(360F)
            animation(image, valueAnimator)
        }

        view.findViewById<View>(R.id.button_scale).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(1F, 1.5F)
            animation(image, valueAnimator)
        }

        view.findViewById<View>(R.id.button_scale_x).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(1F, 1.5F)
            animation(image, valueAnimator)
        }

        view.findViewById<View>(R.id.button_scale_y).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(1F, 1.5F)
            animation(image, valueAnimator)
        }

        view.findViewById<View>(R.id.button_translation).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(0F, -200F)
            animation(image, valueAnimator)
        }

        view.findViewById<View>(R.id.button_translation_x).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(0F, -180F)
            animation(image, valueAnimator)
        }

        view.findViewById<View>(R.id.button_translation_y).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(0F, -180F)
            animation(image, valueAnimator)
        }

        view.findViewById<View>(R.id.button_alpha).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(0.5F)
            animation(image, valueAnimator)
        }

        view.findViewById<View>(R.id.button_move_x).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(150F)
            animation(image, valueAnimator)
        }

        view.findViewById<View>(R.id.button_move_y).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(150F)
            animation(image, valueAnimator)
        }

        view.findViewById<View>(R.id.button_pivot_x).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(50F)
            animation(image, valueAnimator)
        }

        view.findViewById<View>(R.id.button_pivot_y).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(50F)
            animation(image, valueAnimator)
        }

        view.findViewById<View>(R.id.button_object_animator).setOnClickListener {
            objAnimation(ObjectAnimator.ofFloat(image, View.ROTATION, 360f))
        }
    }

    private fun animation(view: View, animator: ValueAnimator) {
        animator.addUpdateListener {
            val value = it.animatedValue as Float
            view.rotation = value
        }
        animator.apply {
            interpolator = CycleInterpolator(5f)
            repeatMode = ValueAnimator.REVERSE
            repeatCount = 2
            duration = DURATION
            start()
        }
    }

    private fun objAnimation(animator: ObjectAnimator) {
        animator.apply {
            interpolator = CycleInterpolator(5f)
            repeatMode = ValueAnimator.REVERSE
            repeatCount = 2
            duration = DURATION
            start()
        }
    }
}