package com.wl.lawyer.mvp.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wl.lawyer.app.mlog

class DividingLineWidget : View {

    private lateinit var paint: Paint
    private var mWidth = 1080
    private var mHeight = 20

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    private fun init(context: Context) {
        paint = Paint()
        paint.isAntiAlias = true
        paint.isDither = true
        paint.color = Color.GRAY
        paint.strokeWidth = 3f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = MeasureSpec.getSize(widthMeasureSpec)
        mHeight = MeasureSpec.getSize(heightMeasureSpec)
        mlog("width(${mWidth}) height(${mHeight})")
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in 0..(mWidth / WIDTH)) {
            if (i % 2 == 0) {

            } else {

            }
        }
    }

    companion object {

        val WIDTH = 10
        val INTERVAl = 3
    }
}
