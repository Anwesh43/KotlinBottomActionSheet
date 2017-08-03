package com.anwesome.ui.kotlinactionsheet

import android.content.Context
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
}