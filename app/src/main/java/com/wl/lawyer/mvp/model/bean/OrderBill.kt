package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OrderBill(
    val id: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("lawyer_id") val lawyerId: Int,
    @SerializedName("order_id") val orderId: Int,
    @SerializedName("order_type") val orderType: Int,
    @SerializedName("total_price") val totalPrice: String,
    @SerializedName("duration") val duration: String,
    @SerializedName("delivery_date") val deliveryDate: String,
    @SerializedName("pay_order_no") val payOrderNo: String,
    @SerializedName("transaction_id") val transactionId: String,
    @SerializedName("user_status") val userStatus: Int,
    @SerializedName("createtime") val createtime: Long,
    @SerializedName("updatetime") val updatetime: Long,
    @SerializedName("pay_status") val payStatus: Int,
    @SerializedName("pay_time") val payTime: Any?
) : Serializable

/*
*
*       "id": 2,
        "user_id": 1,
        "lawyer_id": 1,
        "order_id": 2,
        "order_type": 1,
        "total_price": "111.00",
        "duration": "3天",
        "delivery_date": "2020年4月30日",
        "pay_order_no": "",
        "transaction_id": "",
        "user_status": 0,
        "createtime": 1587440578,
        "updatetime": 1587440578,
        "pay_status": 0,
        "pay_time": null
*
* */