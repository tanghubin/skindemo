package com.skin.demo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import java.util.*


class SkinManager : LayoutInflater.Factory {
    private val viewNameArray = arrayOf("android.widget.", "android.view.", "android.webkit.")

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        val view = createView(name, context, attrs)
        if (view != null) {
            parseSkinAttr(view, context, attrs)
        }
        return view
    }

    private fun parseSkinAttr(view: View, context: Context, attrs: AttributeSet) {
        val skinAttrItems = ArrayList<SkinAttrItem>()
        for (i in 0 until attrs.attributeCount) {
            val attrName = attrs.getAttributeName(i)
            val attrValue = attrs.getAttributeValue(i)
            if ((attrName.contentEquals("background") || attrName.contentEquals("textColor")) && attrValue.startsWith("@")) {
                val id = Integer.parseInt(attrValue.substring(1))
                val attrtype = context.resources.getResourceTypeName(id)
//                val entryName = context.resources.getResourceEntryName(id)
                val skinAttrItem = SkinAttrItem(id, attrName, attrtype)
                skinAttrItems.add(skinAttrItem)
            }
        }
        //保存当前界面需要换肤的view
        if (!skinAttrItems.isEmpty()) {
            val skinItem = SkinViewItem(view, skinAttrItems)
            skinItems.add(skinItem)
        }
    }

    fun skin() {
        for (item in skinItems) {
            item.skin()
        }
    }

    private fun createView(name: String, context: Context, attrs: AttributeSet): View? {
        var view: View? = null
        if (name.contains(".")) {//自定义控件
            view = getView(name, context, attrs)
        } else {//原生控件
            for (i in viewNameArray.indices) {
                val className = viewNameArray[i] + name
                view = getView(className, context, attrs)
                if (view != null) {
                    return view
                }
            }
        }
        return view
    }

    companion object {
        private val skinItems = ArrayList<SkinViewItem>()
    }
}
