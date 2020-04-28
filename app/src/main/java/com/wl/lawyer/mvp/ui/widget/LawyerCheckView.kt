package com.wl.lawyer.mvp.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import com.jess.arms.utils.ArmsUtils
import com.wl.lawyer.R

@SuppressLint("AppCompatCustomView")
class LawyerCheckView : AppCompatButton {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    val mWidth: Int by lazy {
        ArmsUtils.dip2px(context, 287f)
    }

    val mHeight: Int by lazy {
        ArmsUtils.dip2px(context, 50f)
    }

    private fun init() {
        textSize = 18f
        setTextColor(Color.WHITE)
        gravity = Gravity.CENTER
        setBackgroundResource(R.drawable.shape_check_view_bg)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(mWidth, mHeight)
    }

}
