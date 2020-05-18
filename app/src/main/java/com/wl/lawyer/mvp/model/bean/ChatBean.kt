package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ChatBean(
    val id: Int,
    @SerializedName("user_id")val userId: Int,
    @SerializedName("lawyer_id")val lawyerId: Int,
    @SerializedName("order_type")val orderType: Int,
    @SerializedName("order_service")val orderService: Int,
    val createtime: Long,
    val updatetime: Long,
    val status: Int
) : Serializable
/*
*
* {
            "id": 1,
            "user_id": 1,
            "lawyer_id": 1,
            "order_id": 1,
            "order_type": 1,
            "order_service": 3,
            "createtime": 1587388235,
            "updatetime": 1587388235,
            "status": 0
        }
*
* */