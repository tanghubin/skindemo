package com.skin.demo

import android.app.Application
import android.content.Context
import android.os.Environment
import java.io.File

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val skin = getSharedPreferences("skin", Context.MODE_PRIVATE)
        val skinName = skin.getString("skinName", "")
        try {
            SkinResource.loadSkin(
                applicationContext,
                Environment.getExternalStorageDirectory().absolutePath + File.separator + skinName
            )
        } catch (ignored: Exception) {

        }

    }
}
