package com.wl.lawyer.im.call

import com.wl.lawyer.im.message.FeeBean

interface IFeeCallback {
    fun onPositiveClick(data: FeeBean?)
    fun onNegativeClivk(data: FeeBean?)
}