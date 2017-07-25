package com.skumar.animatedtextview

import android.animation.*
import android.content.Context
import android.content.res.TypedArray
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RemoteViews
import android.widget.TextView

/**
 * @author s.kumar on 25/07/2017.
 *
 * Copyright (C)  25/07/2017 Sughosh Krishna Kumar
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

@RemoteViews.RemoteView
open class AnimatedTextView: TextView {
    private val  container: LinearLayout = LinearLayout(context)

    constructor(context: Context): super(context)

    private var attributeArray: TypedArray? = null

    var isPlayTogether: Boolean = false

    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet) {
        attributeArray = context.theme.obtainStyledAttributes(attributeSet, R.styleable.AnimatedTextView, 0, 0)
    }
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int): super(context, attributeSet, defStyleAttr) {
        attributeArray = context.theme.obtainStyledAttributes(attributeSet, R.styleable.AnimatedTextView, 0, 0)
    }
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int = 0, defStyleRes: Int = 0): super(context, attributeSet, defStyleAttr, defStyleRes) {
        attributeArray = context.theme.obtainStyledAttributes(attributeSet, R.styleable.AnimatedTextView, 0, 0)
    }

    init {
        val attrArray = attributeArray
        if (attrArray != null) {
            animatedTextSize = attrArray.getDimensionPixelSize(R.styleable.AnimatedTextView_animatedTextSize, 0).toDouble()
            duration = attrArray.getInteger(R.styleable.AnimatedTextView_duration, 200).toLong()
        }
    }

    var animatedTextSize: Double = 0.0
        set
        get

    var duration = 100L
        set
        get

    private var listOfTextViews: MutableList<TextView>? = null

    private var index: Int = 0

    fun startAnimate() {
        // break the textview down to individual textviews
        // put those into a horizontal layout container
        // animate each textview
        if (context != null){
            listOfTextViews = splitTextToIndividualTextViews()
        }
        if (listOfTextViews != null && listOfTextViews?.isNotEmpty() ?: false){
            val parent = parent as ViewGroup
            container.orientation = LinearLayout.HORIZONTAL
            container.layoutParams = layoutParams
            parent.addView(container)
            text = ""
            iterateAndAdd(container, listOfTextViews?.get(index++))
            parent.removeView(this)
        }
    }

    private fun iterateAndAdd(container: LinearLayout, textView: TextView?) {
        if (textView != null) {
            container.addView(textView)
            val set = AnimatorSet()
            if (isPlayTogether) {
                set.playTogether(customizeAnimation(textView))
            } else {
                set.playSequentially(customizeAnimation(textView))
            }
            set.duration = duration
            set.addListener(object : AnimatorListenerAdapter() {
                var isCancelled = false
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    if (!isCancelled) {
                        if (index < listOfTextViews?.size ?: 0) {
                            iterateAndAdd(container, listOfTextViews?.get(index++))
                        }
                    }
                }

                override fun onAnimationCancel(animation: Animator?) {
                    isCancelled = true
                    super.onAnimationCancel(animation)
                }
            })
            set.start()
        }
    }

    /**
     * This method returns a list of [ObjectAnimator] that is
     * used in playing animation for [AnimatorSet] on each character
     *
     * @NOTE: do not override this method if animator is injected through xml
     */
    protected open fun customizeAnimation(target: View): List<Animator> {
        val attrArray = attributeArray
        val listOfAnimation = mutableListOf<Animator>()
        if (attrArray != null) {
            val animatorRes = attrArray.getResourceId(R.styleable.AnimatedTextView_animator, 0)
            if (animatorRes != 0) {
                val animation = AnimatorInflater.loadAnimator(context, animatorRes) as AnimatorSet
                animation.setTarget(target)
                listOfAnimation.add(animation)
            } else {
                listOfAnimation.add(ObjectAnimator.ofFloat(target, "scaleY", 0f, 0.5f, 0.8f, 1.1f, 1f))
            }
        } else {
            listOfAnimation.add(ObjectAnimator.ofFloat(target, "scaleY", 0f, 0.5f, 0.8f, 1.1f, 1f))
        }
        return listOfAnimation
    }

    private var textToAnimate: CharSequence? = null

    fun animateText(textd: CharSequence?) {
        textToAnimate = textd
    }

    fun animateText(@StringRes res: Int){
        textToAnimate = context.getString(res)
    }

    private fun splitTextToIndividualTextViews(): MutableList<TextView> {
        val listOfChars = mutableListOf<TextView>()
        if (text != null && text.isNotBlank()) {
            text.forEach {
                val textView = TextView(context)
                textView.text = it.toString()
                if (animatedTextSize == 0.0) {
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize/2)
                } else {
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, animatedTextSize.toFloat())
                }
                listOfChars.add(textView)
            }
        }
        return listOfChars
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (textToAnimate != null) {
            text = textToAnimate ?: ""
        }
        if (parent != null) {
            if (parent.asViewGroup()?.containsChild(container) ?: false) {
                parent.asViewGroup()?.removeView(container)
            }
        }
        startAnimate()
    }
}