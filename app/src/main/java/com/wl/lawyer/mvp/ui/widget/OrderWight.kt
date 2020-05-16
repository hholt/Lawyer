package com.wl.lawyer.mvp.ui.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import com.wl.lawyer.R

class OrderWight: LinearLayoutCompat {
    constructor(context: Context): this(context, null)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        initViews()
    }

    fun initViews() {
        inflate(context, R.layout.order_layout, this)

    }


}