package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SpecPriceBean(
    val id: Int,
    val price: String
) : Serializable {
    /*
    "id": 112,
    "price": "2.00"
    */
}