package com.wl.lawyer.mvp.ui.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.wl.lawyer.R

class PasswordEditText : RegexEditText, View.OnTouchListener, View.OnFocusChangeListener,
    TextWatcher {

    private var mCurrentDrawable: Drawable? = null
    private var mVisibleDrawable: Drawable? = null
    private var mInvisibleDrawable: Drawable? = null

    private var mOnTouchListener: OnTouchListener? = null
    private var mOnFocusChangeListener: OnFocusChangeListener? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun initialize(context: Context, attrs: AttributeSet?) {
        super.initialize(context, attrs)

        // Wrap the drawable so that it can be tinted pre Lollipop
        mVisibleDrawable =
            DrawableCompat.wrap(ContextCompat.getDrawable(context, R.drawable.ic_input_show)!!)
        mVisibleDrawable!!.setBounds(
            0,
            0,
            mVisibleDrawable!!.intrinsicWidth,
            mVisibleDrawable!!.intrinsicHeight
        )

        mInvisibleDrawable =
            DrawableCompat.wrap(ContextCompat.getDrawable(context, R.drawable.ic_input_hide)!!)
        mInvisibleDrawable!!.setBounds(
            0,
            0,
            mInvisibleDrawable!!.intrinsicWidth,
            mInvisibleDrawable!!.intrinsicHeight
        )

        mCurrentDrawable = mVisibleDrawable

        // 密码不可见
        addInputType(TYPE_INVISIBLE)
        if (inputRegex == null) {
            // 密码输入规则
            inputRegex = REGEX_NONNULL
        }

        setDrawableVisible(false)
        super.setOnTouchListener(this)
        super.setOnFocusChangeListener(this)
        super.addTextChangedListener(this)
    }

    private fun setDrawableVisible(visible: Boolean) {
        if (mCurrentDrawable!!.isVisible == visible) {
            return
        }

        mCurrentDrawable!!.setVisible(visible, false)
        val drawables = compoundDrawables
        setCompoundDrawables(
            drawables[0],
            drawables[1],
            if (visible) mCurrentDrawable else null,
            drawables[3]
        )
    }

    private fun refreshDrawableStatus() {
        val drawables = compoundDrawables
        setCompoundDrawables(
            drawables[0],
            drawables[1],
            mCurrentDrawable,
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
     * [OnFocusChangeListener]
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
     * [OnTouchListener]
     */

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        val x = motionEvent.x.toInt()
        if (mCurrentDrawable!!.isVisible && x > width - paddingRight - mCurrentDrawable!!.intrinsicWidth) {
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                if (mCurrentDrawable === mVisibleDrawable) {
                    mCurrentDrawable = mInvisibleDrawable
                    // 密码可见
                    removeInputType(TYPE_INVISIBLE)
                    addInputType(TYPE_VISIBLE)
                    refreshDrawableStatus()
                } else if (mCurrentDrawable === mInvisibleDrawable) {
                    mCurrentDrawable = mVisibleDrawable
                    // 密码不可见
                    removeInputType(TYPE_VISIBLE)
                    addInputType(TYPE_INVISIBLE)
                    refreshDrawableStatus()
                }
                val editable = text
                if (editable != null) {
                    setSelection(editable.toString().length)
                }
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
            setDrawableVisible(s.length > 0)
        }
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun afterTextChanged(s: Editable) {}

    companion object {

        private val TYPE_VISIBLE = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        private val TYPE_INVISIBLE = InputType.TYPE_TEXT_VARIATION_PASSWORD
    }
}