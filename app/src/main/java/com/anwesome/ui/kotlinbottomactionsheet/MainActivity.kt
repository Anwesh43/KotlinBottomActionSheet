package com.anwesome.ui.kotlinbottomactionsheet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.anwesome.ui.kotlinactionsheet.ActionSheet
import com.anwesome.ui.kotlinactionsheet.OnActionItemClickListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActionSheet.create(this)
        var txtMsgs = arrayOf("Add","Delete","Sort","Search")
        txtMsgs.forEach {
            ActionSheet.addMenu(it,ActionItemClickListener(this,it))
        }
        ActionSheet.addToParent(this)
    }
    class ActionItemClickListener(var activity: MainActivity,var text:String):OnActionItemClickListener {
        override fun onClick() {
            Toast.makeText(activity,text,Toast.LENGTH_SHORT).show()
        }
    }
}
