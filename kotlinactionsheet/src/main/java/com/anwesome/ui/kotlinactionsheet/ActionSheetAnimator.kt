package com.anwesome.ui.kotlinactionsheet

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.animation.Animation

/**
 * Created by anweshmishra on 03/08/17.
 */
class ActionSheetAnimator(var actionSheet:ActionSheet):AnimatorListenerAdapter(),ValueAnimator.AnimatorUpdateListener {
    var startAnim = ValueAnimator.ofFloat(0.0f,1.0f)
    var dir = 0
    var animated = false
    var endAnim = ValueAnimator.ofFloat(1.0f,0.0f)
    init {
        startAnim.addUpdateListener(this)
        endAnim.addUpdateListener(this)
        startAnim.addListener(this)
        endAnim.addListener(this)
        startAnim.duration = 500
        endAnim.duration = 500
    }
    override fun onAnimationUpdate(vf:ValueAnimator) {
        var factor = vf.animatedValue as Float
        actionSheet.update(factor)
    }
    override fun onAnimationEnd(animator:Animator) {
        if(animated) {
            animated = false
            dir = when(dir) {
                0 -> 1
                1 -> 0
                else -> dir
            }
        }
    }
    fun start() {
        if(!animated) {
            when(dir) {
                0 -> startAnim.start()
                1 -> endAnim.start()
            }
            animated = true
        }
    }
}