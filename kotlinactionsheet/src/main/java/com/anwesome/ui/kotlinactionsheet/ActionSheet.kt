package com.anwesome.ui.kotlinactionsheet

import android.content.Context
import android.graphics.Point
import android.hardware.display.DisplayManager
import android.view.ViewGroup

/**
 * Created by anweshmishra on 03/08/17.
 */
class ActionSheet(ctx:Context,var dw:Int = 0,var dh:Int = 0):ViewGroup(ctx) {
    var actionButton:ActionSheetButtonView?=null
    var animator = ActionSheetAnimator(this)
    var totalH = 0
    init {
        getDimension(ctx)
        actionButton = ActionSheetButtonView(ctx)
        addView(actionButton, LayoutParams(dw/10,dw/10))
    }
    fun update(factor:Float) {
        actionButton?.update(factor)
        actionButton?.setY(9*dh/10-totalH*factor)
    }
    private fun getDimension(ctx:Context) {
        var displayManager = ctx.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        var display = displayManager.getDisplay(0)
        var size = Point()
        display?.getRealSize(size)
        dw = size.x
        dh = size.y
    }
    override fun onLayout(reloaded:Boolean,a:Int,b:Int,w:Int,h:Int) {
        var th = 0
        if(childCount>0) {
            var child = getChildAt(0)
            th = child.measuredHeight
        }
        var y = 9*dh/10 - th
        for(i in 0..childCount-1) {
            var child = getChildAt(i)
            if(i == 0) {
                child.layout(w/2-child.measuredWidth/2,y,w/2+child.measuredWidth/2,y+child.measuredHeight)
            }
            else {
                child.layout(0,y,w,y+child.measuredHeight)
            }
            y+=child.measuredHeight
        }
    }
    override fun onMeasure(wspec:Int,hspec:Int) {
        totalH = 0
        for(i in 0..childCount-1) {
            var child = getChildAt(i)
            measureChild(child,wspec,hspec)
            totalH+=child.height
        }
        setMeasuredDimension(dw,totalH)
    }
}