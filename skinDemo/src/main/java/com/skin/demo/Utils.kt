package com.skin.demo

import android.content.Context
import android.util.AttributeSet
import android.view.View

fun getView(name: String, context: Context, attrs: AttributeSet): View? {
    var view: View? = null
    try {
        val loadClass = context.classLoader.loadClass(name)
        val constructor = loadClass.getConstructor(Context::class.java, AttributeSet::class.java)
        view = constructor.newInstance(context, attrs) as View
    } catch (e: Exception) {

    }

    return view
}