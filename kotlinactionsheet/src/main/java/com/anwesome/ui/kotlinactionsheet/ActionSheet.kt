package com.anwesome.ui.kotlinactionsheet

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.hardware.display.DisplayManager
import android.view.ViewGroup

/**
 * Created by anweshmishra on 03/08/17.
 */
class ActionSheet(ctx:Context,var dw:Int = 0,var dh:Int = 0):ViewGroup(ctx) {
    var actionButton:ActionSheetButtonView?=null
    var actionSheetList:ActionSheetList?=null
    var animator = ActionSheetAnimator(this)
    init {
        getDimension(ctx)
        actionButton = ActionSheetButtonView(ctx)
        actionSheetList = ActionSheetList(ctx)
        addView(actionButton, LayoutParams(dw/10,dw/10))
    }
    fun addMenu(text:String) {
        actionSheetList?.addMenu(text,dw,dh/12)
    }
    fun update(factor:Float) {
        actionButton?.update(factor)
        setPosY(factor)
    }
    private fun getDimension(ctx:Context) {
        var displayManager = ctx.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        var display = displayManager.getDisplay(0)
        var size = Point()
        display?.getRealSize(size)
        dw = size.x
        dh = size.y
    }
    fun setPosY(scale:Float) {
        y = ((0.88f*dh - dw/10)-(measuredHeight-dw/10)*scale)
    }
    override fun onLayout(reloaded:Boolean,a:Int,b:Int,w:Int,h:Int) {
        var y = 0
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
    fun addListToLayout() {
        addView(actionSheetList, LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT))
    }
    override fun onMeasure(wspec:Int,hspec:Int) {
        var totalH = 0
        for(i in 0..childCount-1) {
            var child = getChildAt(i)
            measureChild(child,wspec,hspec)
            totalH+=child.height
        }
        setMeasuredDimension(dw,totalH)
    }
    companion object {
        var sheet:ActionSheet?=null
        fun create(activity:Activity) {
            sheet = ActionSheet(activity)
        }
        fun addMenu(text:String) {
            sheet?.addMenu(text)
        }
        fun addToParent(activity: Activity) {
            sheet?.addListToLayout()
            activity.addContentView(sheet, LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT))
            sheet?.setPosY(0.0f)
        }
    }
}