package com.skin.demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {
    lateinit var skinManager: SkinManager
    override fun onCreate(savedInstanceState: Bundle?) {
        skinManager = SkinManager()
        layoutInflater.factory = skinManager
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ImmersionBar.with(this).titleBar(tv_show).init()
        bt_skin.setOnClickListener {
            val skin = getSharedPreferences("skin", Context.MODE_PRIVATE)
            skin.edit().putString("skinName", "skin.apk").apply()
            SkinResource.loadSkin(
                    this,
                    Environment.getExternalStorageDirectory().absolutePath + File.separator + "skin.apk"
            )
            skinManager.skin()
        }
        skinManager.skin()
        bt_default.setOnClickListener {
            val skin = getSharedPreferences("skin", Context.MODE_PRIVATE)
            skin.edit().putString("skinName", "").apply()
            SkinResource.loadSkin(this, "")
            skinManager.skin()
        }
        bt_jump.setOnClickListener {
            startActivity(Intent(this@MainActivity, TargetActivity::class.java))
        }
        backgroundProgress.setLeftMaxProgress(100f)
        backgroundProgress.setRightMaxProgress(100f)
        backgroundProgress.setLeftProgress(50f)
        backgroundProgress.setRightProgress(50f)
    }

    override fun onDestroy() {
        super.onDestroy()
        ImmersionBar.with(this).destroy()
    }
}
