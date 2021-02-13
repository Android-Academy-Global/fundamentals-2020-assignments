package com.android.academy.fundamentals.workshop03

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator

object ObjectAnimatorHelper {
    fun ofArgb(target: Any, propertyName: String, vararg values: Int): ObjectAnimator {
        val animator = ObjectAnimator.ofInt(target, propertyName, *values)
        animator.setEvaluator(ArgbEvaluator())
        return animator
    }
}