package com.skumar.animatedtextview

import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

/**
 * Created by s.kumar on 25/07/2017.
 */
inline fun ViewParent.asViewGroup(): ViewGroup? {
    if (this is ViewGroup) {
        return this as ViewGroup
    }
    return null
}

inline fun ViewGroup.containsChild(view: View?): Boolean {
    if (view != null) {
        (0..childCount -1)
                .map { getChildAt(it) }
                .filter { it == view }
                .forEach { return true }
    }
    return false
}