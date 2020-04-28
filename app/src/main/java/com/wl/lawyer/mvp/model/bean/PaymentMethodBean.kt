package com.wl.lawyer.mvp.model.bean

import androidx.annotation.DrawableRes

class PaymentMethodBean {
    @DrawableRes
    var paymentRes: Int? = null
    var paymentName: String? = null
    var default: Boolean? = null

    constructor()

    constructor(paymentRes: Int?, paymentName: String?, default: Boolean?) {
        this.paymentRes = paymentRes
        this.paymentName = paymentName
        this.default = default
    }
}
