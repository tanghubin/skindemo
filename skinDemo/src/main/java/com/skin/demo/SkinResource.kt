package com.skin.demo

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.text.TextUtils

object SkinResource {

    private var skinResources: Resources? = null
    private var packageName: String? = null
    private var mResources: Resources? = null

    fun loadSkin(c: Context, filePath: String) {
        try {
            if (TextUtils.isEmpty(filePath)) {
                skinResources = null
                packageName = ""
                return
            }
            mResources = c.resources
            val assetManager = AssetManager::class.java.newInstance()
            @SuppressLint("PrivateApi")
            val method = AssetManager::class.java.getDeclaredMethod("addAssetPath", String::class.java)
            method.isAccessible = true
            method.invoke(assetManager, filePath)
            skinResources = Resources(assetManager, c.resources.displayMetrics, c.resources.configuration)
            packageName = c.packageManager.getPackageArchiveInfo(
                filePath,
                PackageManager.GET_ACTIVITIES or PackageManager.GET_SERVICES
            ).packageName
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun getColor(id: Int): Int {
        if (skinResources != null) {
            val entryName = mResources?.getResourceEntryName(id)
            val resId = skinResources!!.getIdentifier(entryName, "color", packageName)
            if (resId > 0) {
                return skinResources!!.getColor(resId)
            }
        }
        return mResources?.getColor(id)!!

    }

    fun getDrawable(id: Int): Drawable {
        if (skinResources != null) {
            val enteryName = mResources?.getResourceEntryName(id)
            val resId = skinResources!!.getIdentifier(enteryName, "drawable", packageName)
            if (resId > 0) {
                return skinResources!!.getDrawable(resId, null)
            }
        }
        return mResources?.getDrawable(id, null)!!
    }
}
