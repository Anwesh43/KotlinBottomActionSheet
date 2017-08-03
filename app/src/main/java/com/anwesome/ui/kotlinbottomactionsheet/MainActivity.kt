package com.anwesome.ui.kotlinbottomactionsheet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesome.ui.kotlinactionsheet.ActionSheet

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActionSheet.create(this)
        ActionSheet.addToParent(this)
    }
}
