package com.skin.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.gyf.barlibrary.ImmersionBar

class TargetActivity : AppCompatActivity() {
     private lateinit var skinManager: SkinManager

    override fun onCreate(savedInstanceState: Bundle?) {
        skinManager = SkinManager()
        layoutInflater.factory = skinManager
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_target)
        ImmersionBar.with(this).titleBar(findViewById<View>(R.id.tv)).init()
        skinManager.skin()
    }

    override fun onDestroy() {
        super.onDestroy()
        ImmersionBar.with(this).destroy()
    }
}
