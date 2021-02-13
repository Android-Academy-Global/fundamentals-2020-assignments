package com.android.academy.fundamentals.workshop03.solution

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.android.academy.fundamentals.workshop03.ColorsAdapter.ColorViewHolder
import com.android.academy.fundamentals.workshop03.ObjectAnimatorHelper


class SolutionColorItemAnimator: DefaultItemAnimator() {

    private val mAnimatorMap = HashMap<RecyclerView.ViewHolder, AnimatorInfo>()

    override fun canReuseUpdatedViewHolder(viewHolder: RecyclerView.ViewHolder): Boolean = true

    override fun obtainHolderInfo(): ItemHolderInfo {
        return ColorItemHolderInfo()
    }

    override fun animateChange(oldViewHolder: RecyclerView.ViewHolder, newViewHolder: RecyclerView.ViewHolder, preInfo: ItemHolderInfo, postInfo: ItemHolderInfo): Boolean {
        val newHolder = newViewHolder as ColorViewHolder
        val startColor = (preInfo as ColorItemHolderInfo).color
        val newColor = (postInfo as ColorItemHolderInfo).color
        val oldText = preInfo.text
        val newText = postInfo.text
        val fadeToBlack: ObjectAnimator = ObjectAnimatorHelper.ofArgb(newHolder.container, "backgroundColor", startColor, Color.BLACK)
        val fadeFromBlack: ObjectAnimator = ObjectAnimatorHelper.ofArgb(newHolder.container, "backgroundColor", Color.BLACK, newColor)
        val bgAnim = AnimatorSet()
        bgAnim.playSequentially(fadeToBlack, fadeFromBlack)

        val oldTextRotate = ObjectAnimator.ofFloat(newHolder.textView, View.ROTATION_X, 0f, 90f)
        val newTextRotate = ObjectAnimator.ofFloat(newHolder.textView, View.ROTATION_X, -90f, 0f)
        val textAnim = AnimatorSet()
        textAnim.playSequentially(oldTextRotate, newTextRotate)

        oldTextRotate.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                newHolder.textView.text = oldText
            }

            override fun onAnimationEnd(animation: Animator) {
                newHolder.textView.text = newText
            }
        })

        val changeAnim = AnimatorSet()
        changeAnim.playTogether(bgAnim, textAnim)
        changeAnim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                dispatchAnimationFinished(newHolder)
                mAnimatorMap.remove(newHolder)
            }
        })

        val runningInfo = mAnimatorMap[newHolder]
        if (runningInfo != null) {
            val firstHalf = runningInfo.oldTextRotator != null && runningInfo.oldTextRotator.isRunning
            val currentPlayTime = if (firstHalf) runningInfo.oldTextRotator.currentPlayTime else runningInfo.newTextRotator.currentPlayTime
            runningInfo.overallAnim.cancel()
            if (firstHalf) {
                fadeToBlack.currentPlayTime = currentPlayTime
                oldTextRotate.currentPlayTime = currentPlayTime
            } else {
                fadeToBlack.currentPlayTime = fadeToBlack.duration
                oldTextRotate.currentPlayTime = oldTextRotate.duration
                fadeFromBlack.currentPlayTime = currentPlayTime
                newTextRotate.currentPlayTime = currentPlayTime
            }
        }

        val runningAnimInfo = AnimatorInfo(changeAnim, fadeToBlack, fadeFromBlack, oldTextRotate, newTextRotate)
        mAnimatorMap[newHolder] = runningAnimInfo
        changeAnim.start()

        // No other transformations are required.
        // return super.animateChange(oldViewHolder, newViewHolder, preInfo, postInfo);

        return false
    }

    internal class ColorItemHolderInfo : ItemHolderInfo() {
        var color = 0
        var text: String? = null

        override fun setFrom(viewHolder: RecyclerView.ViewHolder, @AdapterChanges flags: Int): ItemHolderInfo {
            super.setFrom(viewHolder, flags)
            val holder = viewHolder as ColorViewHolder
            color = (holder.container.background as ColorDrawable).color
            text = holder.textView.text as String
            return this
        }
    }

    data class AnimatorInfo(
            var overallAnim: Animator,
            var fadeToBlackAnim: ObjectAnimator,
            var fadeFromBlackAnim: ObjectAnimator,
            var oldTextRotator: ObjectAnimator,
            var newTextRotator: ObjectAnimator)
}