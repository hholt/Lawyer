package com.wl.lawyer.mvp.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import com.youth.banner.indicator.BaseIndicator

class RectangleIndicator : BaseIndicator {
    private var mNormalWidth: Float = 0.toFloat()
    private var mSelectedWidth: Float = 0.toFloat()
    private var mMaxWidth: Float = 0.toFloat()

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        mNormalWidth = config.normalWidth
        mSelectedWidth = config.selectedWidth
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val count = config.indicatorSize
        if (count <= 1) return
        mNormalWidth = config.normalWidth
        mSelectedWidth = config.selectedWidth

        //考虑当 选中和默认 的大小不一样的情况
        mMaxWidth = Math.max(mSelectedWidth, mNormalWidth)
        //间距*（总数-1）+最大的半径（考虑有时候选中时会变大的情况）+默认半径*（总数-1）
        val width =
            ((count - 1) * config.indicatorSpace + (mMaxWidth + mNormalWidth * (count - 1))).toInt()
        val height = 10
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val count = config.indicatorSize
        if (count <= 1) return
        for (i in 0 until count) {
            mPaint.color =
                if (config.currentPosition == i) config.selectedColor else config.normalColor
            val w = if (config.currentPosition == i) mSelectedWidth else mNormalWidth
            val x =  (mNormalWidth + config.indicatorSpace) * i
            //            canvas.drawCircle(x, mMaxWidth, radius, mPaint);
            canvas.drawRoundRect(x, 0f, x + w, 5f, 0f, 0f, mPaint)
        }
    }
}
