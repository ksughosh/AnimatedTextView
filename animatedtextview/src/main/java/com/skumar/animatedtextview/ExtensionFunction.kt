package com.skumar.animatedtextview

import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

/**
 * @author s.kumar on 07/07/2017.
 *
 * Copyright (C)  07/07/2017 Sughosh Krishna Kumar
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE SUGHOSH KRISHNA KUMAR BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
