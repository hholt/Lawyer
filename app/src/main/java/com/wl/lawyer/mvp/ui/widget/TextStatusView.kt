package com.wl.lawyer.mvp.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import android.util.AttributeSet
import android.widget.TextView
import com.jess.arms.utils.ArmsUtils
import com.wl.lawyer.R
import java.util.logging.Logger

class TextStatusView : TextView {

    val logger = Logger.getLogger("TextStatusView")

    private val CIRCLE_INTERVAL = 40
    private val CIRCLE_RADIUS_B = 14f
    private val CIRCLE_RADIUS_S = 8f

    var mArray: ArrayList<String>? = null
    var mTextColor: Int = Color.parseColor("#0D66E9") // 文字选中的颜色
    var mTextUnColor: Int = Color.parseColor("#8C9DB5") // 文字未选中的颜色
    var mColor: Int = Color.parseColor("#F8CF00") // 图标选中的颜色
    var mUnColor: Int = Color.parseColor("#D7E1EC") // 图标未选中的颜色
    var mWidth: Int = 1080
    var mHeight: Int = 1920

    var mTextPaint: TextPaint = TextPaint()
    var mPaint: Paint = Paint()
    var mTextRects: ArrayList<Rect> = ArrayList()
    var baseY = 0f

    var curPosition: Int = 0

    init {
        mTextPaint.isAntiAlias = true
        mTextPaint.isDither = true
        mPaint.isAntiAlias = true
        mPaint.isDither = true

        mTextPaint.textSize = 48f
        mPaint.style = Paint.Style.FILL
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context?, attrs: AttributeSet?) {
        var a = context?.obtainStyledAttributes(attrs, R.styleable.TextStatusView)
        mArray = a?.getTextArray(R.styleable.TextStatusView_array)?.toList() as ArrayList<String>
        mTextColor = a?.getColor(R.styleable.TextStatusView_text_color, mTextColor)
        mTextUnColor = a?.getColor(R.styleable.TextStatusView_text_un_color, mTextUnColor)
        mColor = a?.getColor(R.styleable.TextStatusView_circle_color, mColor)
        mUnColor = a?.getColor(R.styleable.TextStatusView_circle_un_color, mUnColor)
        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = MeasureSpec.getSize(widthMeasureSpec)
        mHeight = MeasureSpec.getSize(heightMeasureSpec)
        logger.info("width:$mWidth height:$mHeight")
        setMeasuredDimension(mWidth, mHeight)
        calRects()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas.let {
            drawText(canvas!!)
            drawCircle(canvas!!)
        }
    }

    private fun calRects() {
        mArray.let {
            mTextRects.clear()
            var length = 0
            // 计算所有文字的大小rect
            for (i in 0 until mArray!!.size) {
                var str: String = mArray!![i]
                var rect = Rect()
                mTextPaint.getTextBounds(str, 0, str.length, rect)// 获取文字大小
                mTextRects.add(rect)
                length += (rect.right + rect.left)
            }
            var interval: Int = (mWidth - ArmsUtils.dip2px(context, paddingLeft.toFloat()) -
                    ArmsUtils.dip2px(context, paddingRight.toFloat()) - length) /
                    (mTextRects.size - 1)// 每个文字之间的间隔
            // 重新计算所有文字的位置
            for (i in 0 until mTextRects!!.size) {
                var rect = mTextRects[i]
                if (i == 0) {
                    rect.left += ArmsUtils.dip2px(context, paddingLeft.toFloat())
                    rect.right += rect.left
                    continue
                }
                var preRect = mTextRects[i - 1]
                rect.left += (preRect.right + interval)
                rect.right += rect.left
                mTextRects[i] = rect
            }
        }
    }

    private fun drawText(canvas: Canvas) {
        if (mArray == null) return
        for (i in 0 until mArray!!.size) {
            var str: String = mArray!![i]
            baseY = ((mHeight / 3f) - ((mTextPaint.descent() + mTextPaint.ascent()) / 2))
            var rect = mTextRects[i]
            if (i == curPosition) {
                mTextPaint.color = mTextColor
            } else {
                mTextPaint.color = mTextUnColor
            }
            canvas.drawText(
                str,
                rect.left * 1f,
                baseY,
                mTextPaint
            )
        }
    }

    private fun drawCircle(canvas: Canvas) {
        val startX = (mTextRects[0].right + mTextRects[0].left) / 2
        val endX =
            (mTextRects[mTextRects.size - 1].right + mTextRects[mTextRects.size - 1].left) / 2

        val pointCount = ((endX - startX) / CIRCLE_INTERVAL) + 1
        val pointInterval = pointCount / (mTextRects.size - 1)

        for ((count, x) in (startX until endX step CIRCLE_INTERVAL).withIndex()) {
            if (count == 0 || count == pointCount - 1) {
                mPaint.color = mColor
                canvas.drawCircle(x * 1f, baseY + 50, CIRCLE_RADIUS_B, mPaint)
            } else if (count % pointInterval == 0) {
                if (Math.abs(pointCount - count) >= pointInterval) {
                    mPaint.color = mColor
                    canvas.drawCircle(x * 1f, baseY + 50, CIRCLE_RADIUS_B, mPaint)
                } else {
                    mPaint.color = mUnColor
                    canvas.drawCircle(x * 1f, baseY + 50, CIRCLE_RADIUS_S, mPaint)
                }
            } else {
                mPaint.color = mUnColor
                canvas.drawCircle(x * 1f, baseY + 50, CIRCLE_RADIUS_S, mPaint)
            }
        }
    }

    fun preview() {
        if (mArray == null) return
        curPosition = if (curPosition < 0) {
            0
        } else {
            --curPosition
        }
        invalidate()
    }

    fun next() {
        if (mArray == null) return
        curPosition = if (curPosition > mArray!!.size - 1) {
            mArray!!.size - 1
        } else {
            ++curPosition
        }
        invalidate()
    }

}
