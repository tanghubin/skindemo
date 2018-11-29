package com.skin.demo

import android.view.View
import android.widget.TextView


class SkinViewItem(var view: View, var skinAttrItemList: List<SkinAttrItem>) {

    /**
     * 换肤
     */
    fun skin() {
        skinAttrItemList.forEach {
            when (it.attrName) {
                "background" -> when (it.attrType) {
                    "drawable" -> view.setBackgroundDrawable(SkinResource.getDrawable(it.id))
                    "color" -> view.setBackgroundColor(SkinResource.getColor(it.id))
                    else -> {
                    }
                }
                "textColor"//TextView
                -> (view as TextView).setTextColor(SkinResource.getColor(it.id))
                else -> {
                }
            }
        }
    }

}
