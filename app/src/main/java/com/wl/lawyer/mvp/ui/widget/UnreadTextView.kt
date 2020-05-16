package com.wl.lawyer.mvp.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.lxj.androidktx.core.dp2px
import com.wl.lawyer.R

class UnreadTextView : AppCompatTextView {
    constructor(context: Context): this(context, null)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        init()
    }

    private val mNormalSize = context.dp2px(16f)
    private var mPaint: Paint? = null



    private fun init() {
        mPaint = Paint()
        mPaint!!.color = ContextCompat.getColor(context, R.color.read_dot_bg)
        setTextColor(Color.WHITE)
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 10f)
    }


    override fun onDraw(canvas: Canvas) {
        if (text.length === 0) {
            // 没有字符，就在本View中心画一个小圆点
            val l = (measuredWidth - context.dp2px(10f)) / 2
            val r = measuredWidth - l
            canvas.drawOval(RectF(l.toFloat(), l.toFloat(), r.toFloat(), r.toFloat()), mPaint)
        } else if (text.length === 1) {
            canvas.drawOval(RectF(0f, 0f, mNormalSize.toFloat(), mNormalSize.toFloat()), mPaint)
        } else if (text.length > 1) {
            canvas.drawRoundRect(
                RectF(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat()),
                (measuredHeight / 2).toFloat(),
                (measuredHeight / 2).toFloat(),
                mPaint
            )
        }
        super.onDraw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var width = mNormalSize
        val height = mNormalSize
        if (text.length > 1) {
            width = mNormalSize + context.dp2px((text.length - 1) * 10f)
        }
        setMeasuredDimension(width, height)
    }
}