package com.anwesome.ui.kotlinactionsheet

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

/**
 * Created by anweshmishra on 03/08/17.
 */
class ActionSheetList(ctx:Context):ViewGroup(ctx) {
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var y = 0
        for(i in 0..childCount-1) {
            var child = getChildAt(i)
            child.layout(0,y,child.measuredWidth,y+child.measuredHeight)
            y += child.measuredHeight
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var totalH = 0
        var w = 0
        for(i in 0..childCount-1) {
            var child = getChildAt(i)
            measureChild(child,widthMeasureSpec,heightMeasureSpec)
            totalH += child.measuredHeight
            w = Math.max(w,child.measuredWidth)
        }
        setMeasuredDimension(w,totalH)
    }
    fun addMenu(text:String,w:Int,h:Int) {
        var menu = ActionSheetMenu(context,text)
        addView(menu, LayoutParams(w,h))
    }
    class ActionSheetMenu(ctx:Context,var text:String,var scale:Float = 0.0f):View(ctx) {
        var animator = MenuAnimator(this)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        override fun onDraw(canvas:Canvas) {
            var w = canvas.width.toFloat()
            var h = canvas.height.toFloat()
            paint.color = Color.GRAY
            canvas.drawRect(0.0f,0.0f,w,h,paint)
            paint.color = Color.WHITE
            paint.textSize = w/20
            canvas.drawText(text,w/2-paint.measureText(text)/2,h/2,paint)
            canvas.save()
            canvas.translate(w/2,h/2)
            canvas.scale(scale,scale)
            paint.color = Color.argb(150,0,0,0)
            canvas.drawRect(-w/2,-h/2,w/2,h/2,paint)
            canvas.restore()

        }
        fun update(factor:Float) {
            scale = factor
            postInvalidate()
        }

        override fun onTouchEvent(event: MotionEvent): Boolean {
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    animator.start()
                }
            }
            return true
        }
        class MenuAnimator(var v:ActionSheetMenu):AnimatorListenerAdapter(),ValueAnimator.AnimatorUpdateListener {
            var startAnim = ValueAnimator.ofFloat(0.0f,1.0f)
            var endAnim = ValueAnimator.ofFloat(1.0f,0.0f)
            var dir = 0
            var animated = false
            init {
                endAnim.addUpdateListener(this)
                startAnim.addUpdateListener(this)
                endAnim.addListener(this)
                startAnim.addListener(this)
                endAnim.duration = 500
                startAnim.duration = 500
            }
            override fun onAnimationUpdate(vf:ValueAnimator) {
                v.update(vf.animatedValue as Float)
            }
            override fun onAnimationEnd(animator:Animator) {
                if(animated) {
                    when(dir) {
                        0 -> {
                            endAnim.start()
                            dir = 1
                        }
                        1 -> {
                            dir = 0
                            animated = false
                        }
                    }
                }
            }
            fun start() {
                if(!animated) {
                    animated = true
                    startAnim.start()
                }
            }
        }
    }
}