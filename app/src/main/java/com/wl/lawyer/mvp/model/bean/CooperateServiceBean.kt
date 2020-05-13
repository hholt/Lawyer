package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CooperateServiceBean(
    val id: Int,
    @SerializedName("service_name") val name: String,
    @SerializedName("service_price") val price: String,
    val weigh: Int
) : Serializable
/*
*
* "id": 2,
  "service_name": "律师协调纠纷处理",
  "service_price": "1100.00",
  "weigh": 2
*
* */