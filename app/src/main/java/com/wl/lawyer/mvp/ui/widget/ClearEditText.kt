package com.wl.lawyer.mvp.ui.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.wl.lawyer.R

class ClearEditText : RegexEditText, View.OnTouchListener, View.OnFocusChangeListener, TextWatcher {

    private var mClearDrawable: Drawable? = null

    private var mOnTouchListener: OnTouchListener? = null
    private var mOnFocusChangeListener: OnFocusChangeListener? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun initialize(context: Context, attrs: AttributeSet?) {
        super.initialize(context, attrs)

        // Wrap the drawable so that it can be tinted pre Lollipop
        mClearDrawable =
            DrawableCompat.wrap(ContextCompat.getDrawable(context, R.drawable.ic_input_delete)!!)
        mClearDrawable!!.setBounds(
            0,
            0,
            mClearDrawable!!.intrinsicWidth,
            mClearDrawable!!.intrinsicHeight
        )
        setDrawableVisible(false)
        super.setOnTouchListener(this)
        super.setOnFocusChangeListener(this)
        super.addTextChangedListener(this)
    }

    private fun setDrawableVisible(visible: Boolean) {
        if (mClearDrawable!!.isVisible == visible) {
            return
        }

        mClearDrawable!!.setVisible(visible, false)
        val drawables = compoundDrawables
        setCompoundDrawables(
            drawables[0],
            drawables[1],
            if (visible) mClearDrawable else null,
            drawables[3]
        )
    }

    override fun setOnFocusChangeListener(onFocusChangeListener: OnFocusChangeListener) {
        mOnFocusChangeListener = onFocusChangeListener
    }

    override fun setOnTouchListener(onTouchListener: OnTouchListener) {
        mOnTouchListener = onTouchListener
    }

    /**
     * [View.OnFocusChangeListener]
     */

    override fun onFocusChange(view: View, hasFocus: Boolean) {
        if (hasFocus && text != null) {
            setDrawableVisible(text!!.isNotEmpty())
        } else {
            setDrawableVisible(false)
        }
        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener!!.onFocusChange(view, hasFocus)
        }
    }

    /**
     * [View.OnTouchListener]
     */

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        val x = motionEvent.x.toInt()
        if (mClearDrawable!!.isVisible && x > width - paddingRight - mClearDrawable!!.intrinsicWidth) {
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                setText("")
            }
            return true
        }
        return mOnTouchListener != null && mOnTouchListener!!.onTouch(view, motionEvent)
    }

    /**
     * [TextWatcher]
     */

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (isFocused) {
            setDrawableVisible(s.isNotEmpty())
        }
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun afterTextChanged(s: Editable) {}
}