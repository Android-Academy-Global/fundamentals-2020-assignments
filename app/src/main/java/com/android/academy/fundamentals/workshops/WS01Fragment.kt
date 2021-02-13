package com.android.academy.fundamentals.workshops

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.R

class WS01Fragment : Fragment(R.layout.fragment_ws01) {

    companion object {
        // TODO add DURATION
    }

    private lateinit var image: ImageView
    // TODO 01 set rotation for Image
    // TODO 02 set rotationX for Image
    // TODO 03 set rotationY for Image
    // TODO 04 set scaleX and scaleY for Image
    // TODO 05 set scaleX for Image
    // TODO 06 set scaleY for Image
    // TODO 07 set translationX and translationY for Image
    // TODO 08 set translationX for Image
    // TODO 09 set translationY for Image
    // TODO 10 set alpha from 1f to 0.5f for Image
    // TODO 11 set position of X in pixel for move Image
    // TODO 12 set position of Y in pixel for move Image
    // TODO 13 set pivotX
    // TODO 14 set pivotY
    // TODO 15 add interpolator one of the (AccelerateInterpolator | DecelerateInterpolator | AccelerateDecelerateInterpolator |
    //  AnticipateInterpolator | AnticipateOvershootInterpolator | OvershootInterpolator | CycleInterpolator | BounceInterpolator)
    //  and look at changes
    // TODO 16 add repeatMode (RESTART | REVERSE | INFINITE) and repeatCount for valueAnimator
    // TODO 17 replace animator to ObjectAnimator
    //  use construction ObjectAnimator.ofFloat(view, Property<T, Float> property, varargs values)
    //  Example: ObjectAnimator.ofFloat(image, View.ROTATION, 0, 360f)
    //  don't forget about duration and call start() for animation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image = view.findViewById(R.id.image)

        view.findViewById<View>(R.id.button_rotation).setOnClickListener {
            // TODO 01 call method the animation(view, animator) using ValueAnimator for implementation rotation
        }

        view.findViewById<View>(R.id.button_rotation_x).setOnClickListener {
            // TODO 02 call method the animation(view, animator) using ValueAnimator implementation rotationX
        }

        view.findViewById<View>(R.id.button_rotation_y).setOnClickListener {
            // TODO 03 call method the animation(view, animator) using ValueAnimator implementation rotationY
        }

        view.findViewById<View>(R.id.button_scale).setOnClickListener {
            // TODO 04 call method the animation(view, animator) using ValueAnimator implementation scale
        }

        view.findViewById<View>(R.id.button_scale_x).setOnClickListener {
            // TODO 05 call method the animation(view, animator) using ValueAnimator implementation scaleX
        }

        view.findViewById<View>(R.id.button_scale_y).setOnClickListener {
            // TODO 06 call method the animation(view, animator) using ValueAnimator implementation scaleY
        }

        view.findViewById<View>(R.id.button_translation).setOnClickListener {
            // TODO 07 call method the animation(view, animator) using ValueAnimator implementation translation
        }

        view.findViewById<View>(R.id.button_translation_x).setOnClickListener {
            // TODO 08 call method the animation(view, animator) using ValueAnimator implementation translationX
        }

        view.findViewById<View>(R.id.button_translation_y).setOnClickListener {
            // TODO 09 call method the animation(view, animator) using ValueAnimator implementation translationY
        }

        view.findViewById<View>(R.id.button_alpha).setOnClickListener {
            // TODO 10 call method the animation(view, animator) using ValueAnimator implementation alpha
        }

        view.findViewById<View>(R.id.button_move_x).setOnClickListener {
            // TODO 11 call method the animation(view, animator) using ValueAnimator implementation move X position
        }

        view.findViewById<View>(R.id.button_move_y).setOnClickListener {
            // TODO 12 call method the animation(view, animator) using ValueAnimator implementation move Y position
        }

        view.findViewById<View>(R.id.button_pivot_x).setOnClickListener {
            // TODO 13 call method the animation(view, animator) using ValueAnimator implementation pivotX
        }

        view.findViewById<View>(R.id.button_pivot_y).setOnClickListener {
            // TODO 14 call method the animation(view, animator) using ValueAnimator implementation pivotY
        }

        view.findViewById<View>(R.id.button_object_animator).setOnClickListener{
            // TODO 17 call method the objAnimation(animator) using ObjectAnimator
        }
    }

    private fun animation(view: View, animator: ValueAnimator) {
        // TODO add listener and action if you need
        animator.apply {
            // TODO 15 add interpolator for your animator
            // TODO 16 add repeatMode and repeatCount for your animator
            // TODO add duration for animator
            // TODO start you animator
        }
    }

    private fun objAnimation(animator: ObjectAnimator) {
        animator.apply {
            // TODO add interpolator if you needed
            // TODO add repeatMode and repeatCount if you needed
            // TODO add duration for animator
            // TODO start you animator
        }
    }
}