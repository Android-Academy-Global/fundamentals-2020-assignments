package com.android.academy.fundamentals.workshops.solution

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.CycleInterpolator
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.R

class WS01SolutionFragment: Fragment(R.layout.fragment_ws01) {

    companion object {

        private const val DURATION = 2000L
    }
    
    private lateinit var image: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image = view.findViewById(R.id.image)

        view.findViewById<View>(R.id.button_rotation).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(360F)
            // TODO 15 add interpolator
            valueAnimator.interpolator = CycleInterpolator(5f)
            // TODO 16 add repeatMode and repeatCount
            valueAnimator.repeatMode = ValueAnimator.REVERSE
            valueAnimator.repeatCount = 2
            valueAnimator.addUpdateListener {
                val value = it.animatedValue as Float
                // TODO 01 set rotation
                image.rotation = value
            }
            valueAnimator.duration = DURATION
            valueAnimator.start()
        }

        view.findViewById<View>(R.id.button_rotation_x).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(360F)
            valueAnimator.addUpdateListener {
                val value = it.animatedValue as Float
                // TODO 02 set rotationX
                image.rotationX = value
            }
            valueAnimator.duration = DURATION
            valueAnimator.start()
        }

        view.findViewById<View>(R.id.button_rotation_y).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(360F)
            valueAnimator.addUpdateListener {
                val value = it.animatedValue as Float
                // TODO 03 set rotationY
                image.rotationY = value
            }
            valueAnimator.duration = DURATION
            valueAnimator.start()
        }

        view.findViewById<View>(R.id.button_scale).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(1F, 1.5F)
            valueAnimator.addUpdateListener {
                val value = it.animatedValue as Float
                // TODO 04 set scaleX and scaleY
                image.scaleX = value
                image.scaleY = value
            }

            valueAnimator.duration = DURATION
            valueAnimator.start()
        }

        view.findViewById<View>(R.id.button_scale_x).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(1F, 1.5F)
            valueAnimator.addUpdateListener {
                val value = it.animatedValue as Float
                // TODO 05 set scaleX
                image.scaleX = value
            }

            valueAnimator.duration = DURATION
            valueAnimator.start()
        }

        view.findViewById<View>(R.id.button_scale_y).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(1F, 1.5F)
            valueAnimator.addUpdateListener {
                val value = it.animatedValue as Float
                // TODO 06 set scaleY
                image.scaleY = value
            }

            valueAnimator.duration = DURATION
            valueAnimator.start()
        }

        view.findViewById<View>(R.id.button_translation).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(0F, -200F)
            valueAnimator.addUpdateListener {
                val value = it.animatedValue as Float
                // TODO 07 set translationX and translationY
                image.translationX = value
                image.translationY = value
            }
            valueAnimator.duration = DURATION
            valueAnimator.start()
        }

        view.findViewById<View>(R.id.button_translation_x).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(0F, -180F)
            valueAnimator.addUpdateListener {
                val value = it.animatedValue as Float
                // TODO 08 set translationX
                image.translationX = value
            }
            valueAnimator.duration = DURATION
            valueAnimator.start()
        }

        view.findViewById<View>(R.id.button_translation_y).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(0F, -180F)
            valueAnimator.addUpdateListener {
                val value = it.animatedValue as Float
                // TODO 09 set translationY
                image.translationY = value
            }
            valueAnimator.duration = DURATION
            valueAnimator.start()
        }

        view.findViewById<View>(R.id.button_alpha).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(0.5F)
            valueAnimator.addUpdateListener {
                val value = it.animatedValue as Float
                // TODO 10 set alpha
                image.alpha = value
            }

            valueAnimator.duration = DURATION
            valueAnimator.start()
        }

        view.findViewById<View>(R.id.button_move_x).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(150F)
            valueAnimator.addUpdateListener {
                val value = it.animatedValue as Float
                // TODO 11 set x
                image.x = value
            }

            valueAnimator.duration = DURATION
            valueAnimator.start()
        }

        view.findViewById<View>(R.id.button_move_y).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(150F)
            valueAnimator.addUpdateListener {
                val value = it.animatedValue as Float
                // TODO 12 set y
                image.y = value
            }

            valueAnimator.duration = DURATION
            valueAnimator.start()
        }

        view.findViewById<View>(R.id.button_pivot_x).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(50F)
            valueAnimator.addUpdateListener {
                val value = it.animatedValue as Float
                // TODO 13 set pivotX
                image.pivotX = value
            }

            valueAnimator.duration = DURATION
            valueAnimator.start()
        }

        view.findViewById<View>(R.id.button_pivot_y).setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(50F)
            valueAnimator.addUpdateListener {
                val value = it.animatedValue as Float
                // TODO 14 set pivotY
                image.pivotY = value
            }

            valueAnimator.duration = DURATION
            valueAnimator.start()
        }

        // TODO 16 replace animator to ObjectAnimator
        //  use construction ObjectAnimator.ofFloat(view, Property<T, Float> property, varargs values)
        //  Example: ObjectAnimator.ofFloat(image, View.ROTATION, 0, 360f)
        //  don't forget about duration and call start() for animation
        //        ObjectAnimator.ofFloat(image, View.ROTATION, 360f).start()
        //        ObjectAnimator.ofFloat(image, View.ROTATION_X, 360f).start()
        //        ObjectAnimator.ofFloat(image, View.ROTATION_Y, 360f).start()
        //
        //        ObjectAnimator.ofFloat(image, View.SCALE_X, 1f).start()
        //        ObjectAnimator.ofFloat(image, View.SCALE_Y, 1f).start()
        //
        //        ObjectAnimator.ofFloat(image, View.TRANSLATION_X, 0f, -360f).start()
        //        ObjectAnimator.ofFloat(image, View.TRANSLATION_Y, 0f, -360f).start()
        //
        //        ObjectAnimator.ofFloat(image, View.ALPHA, 0.5f).start()
        //        ObjectAnimator.ofFloat(image, View.X, 150f).start()
        //        ObjectAnimator.ofFloat(image, View.Y, -150f).start()

        // TODO 16* call static method <loadAnimator> of AnimatorInflater
        //  animation files are located in the folder res/anim
    }
}