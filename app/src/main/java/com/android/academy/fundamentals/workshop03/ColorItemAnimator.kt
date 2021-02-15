package com.android.academy.fundamentals.workshop03

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.android.academy.fundamentals.workshop03.solution.SolutionColorItemAnimator

class ColorItemAnimator: DefaultItemAnimator() {

    private val mAnimatorMap = HashMap<RecyclerView.ViewHolder, SolutionColorItemAnimator.AnimatorInfo>()

    // TODO 03 reuse updated view holder
    //  override canReuseUpdatedViewHolder(...)
    //  return true value

    override fun obtainHolderInfo(): ItemHolderInfo {
        return ColorItemHolderInfo()
    }

    override fun animateChange(oldViewHolder: RecyclerView.ViewHolder, newViewHolder: RecyclerView.ViewHolder, preInfo: ItemHolderInfo, postInfo: ItemHolderInfo): Boolean {
        val newHolder = newViewHolder as ColorsAdapter.ColorViewHolder
        val startColor = (preInfo as ColorItemHolderInfo).color
        val newColor = (postInfo as ColorItemHolderInfo).color
        val oldText = preInfo.text
        val newText = postInfo.text
        val fadeToBlack: ObjectAnimator = ObjectAnimatorHelper.ofArgb(newHolder.container, "backgroundColor", startColor, Color.BLACK)
        val fadeFromBlack: ObjectAnimator = ObjectAnimatorHelper.ofArgb(newHolder.container, "backgroundColor", Color.BLACK, newColor)
        val bgAnim = AnimatorSet()
        // TODO 04 set up how to play background animation
        //  use playSequentially(fadeToBlack, fadeFromBlack)

        val oldTextRotate = ObjectAnimator.ofFloat(newHolder.textView, View.ROTATION_X, 0f, 90f)
        val newTextRotate = ObjectAnimator.ofFloat(newHolder.textView, View.ROTATION_X, -90f, 0f)
        val textAnim = AnimatorSet()
        // TODO 05 set up how to play text animation
        //  use playSequentially(oldTextRotate, newTextRotate)

        oldTextRotate.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                newHolder.textView.text = oldText
            }

            override fun onAnimationEnd(animation: Animator) {
                newHolder.textView.text = newText
            }
        })

        val changeAnim = AnimatorSet()
        // TODO 06 set up playing two animations together
        //  use playTogether(bgAnim, textAnim)
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

        val runningAnimInfo = SolutionColorItemAnimator.AnimatorInfo(changeAnim, fadeToBlack, fadeFromBlack, oldTextRotate, newTextRotate)
        mAnimatorMap[newHolder] = runningAnimInfo
        // TODO 07 start animation

        // No other transformations are required.
        // return super.animateChange(oldViewHolder, newViewHolder, preInfo, postInfo);

        return false
    }

    internal class ColorItemHolderInfo : ItemHolderInfo() {
        var color = 0
        var text: String? = null

        override fun setFrom(viewHolder: RecyclerView.ViewHolder, @AdapterChanges flags: Int): ItemHolderInfo {
            super.setFrom(viewHolder, flags)
            val holder = viewHolder as ColorsAdapter.ColorViewHolder
            color = (holder.container.background as ColorDrawable).color
            text = holder.textView.text as String
            return this
        }
    }
}