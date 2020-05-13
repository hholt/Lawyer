package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PayOrderBean<T>(
    val order: T,
    val sign: String,
    @SerializedName("pay_way") val payWay: String
) : Serializable

/*
     "order": {
     "sign": "",
     "pay_way": "money"
*
* */