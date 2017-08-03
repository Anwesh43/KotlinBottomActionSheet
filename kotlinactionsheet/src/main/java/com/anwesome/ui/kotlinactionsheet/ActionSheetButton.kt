package com.anwesome.ui.kotlinactionsheet

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

/**
 * Created by anweshmishra on 03/08/17.
 */
class ActionSheetButtonView(ctx:Context):View(ctx) {
    val paint:Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var scale:Float = 0.0f
    val renderer = Renderer(this)
    override fun onDraw(canvas:Canvas) {
        renderer.render(canvas,paint,0.0f)
    }
    fun update(scale:Float) {
        this.scale = scale
        postInvalidate()
    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                renderer.handleTap()
            }
        }
        return true
    }
    class Renderer(var v:ActionSheetButtonView) {
        var time = 0
        var button:ActionSheetButton?=null
        fun render(canvas:Canvas,paint:Paint,scale:Float) {
            if(time == 0) {
                button = ActionSheetButton(canvas.width.toFloat()/2,canvas.height.toFloat()/2,canvas.width.toFloat()/2)
            }
            button?.draw(canvas,paint,scale)
            time++
        }
        fun handleTap() {
            var parent =  v.parent as ActionSheet
            parent.animator.start()
        }
    }
   data class ActionSheetButton(var x:Float,var y:Float,var r:Float) {
       fun draw(canvas:Canvas,paint:Paint,scale:Float) {
           canvas.save()
           canvas.translate(x,y)
           canvas.rotate(90.0f)
           paint.color = Color.GRAY
           canvas.drawCircle(0.0f,0.0f,r,paint)
           paint.color = Color.WHITE
           paint.strokeWidth = r/15
           paint.strokeCap = Paint.Cap.ROUND
           for(i in 0..1) {
               canvas.save()
               canvas.rotate(i*90.0f)
               canvas.drawLine(0.0f,-r/2,0.0f,r/2,paint)
               canvas.restore()
           }
           canvas.restore()
       }
   }
}