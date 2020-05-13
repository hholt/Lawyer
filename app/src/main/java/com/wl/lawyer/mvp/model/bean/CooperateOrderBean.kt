package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CooperateOrderBean(
    @SerializedName("order_no") val orderNo: String,
    @SerializedName("user_id") val uId: Int,
    @SerializedName("service_id") val serviceId: Int,
    @SerializedName("service_name") val serviceName: String,
    @SerializedName("service_content") val serviceContent: String,
    @SerializedName("service_price") val servicePrice: String,
    @SerializedName("other_price") val otherPrice: String,
    @SerializedName("total_price") val totalPrice: String,
    @SerializedName("lawyer_id") val lawyerId: Int,
    @SerializedName("lawyer_status") val lawyerStatus: Int,
    @SerializedName("createtime") val createtime: Long,
    @SerializedName("updatetime") val updatetime: Long,
    val id: Int,
    @SerializedName("lawyer_status_text") val lawyerStatusText: String,
    @SerializedName("pay_status_text") val payStatusText: String,
    @SerializedName("pay_time_text") val payTimeText: String,
    @SerializedName("order_status_text") val order_status_text: String?
) : Serializable
/*
*
*   "order_no": "LC20200513155745799322",
    "user_id": 27,
    "service_id": 1,
    "service_name": "律师陪同谈判、纠纷处理",
    "service_content": "",
    "service_price": "2000.00",
    "other_price": 300,
    "total_price": 2300,
    "lawyer_id": "13",
    "lawyer_status": 0,
    "pay_status": 0,
    "createtime": 1589356665,
    "updatetime": 1589356665,
    "id": "15",
    "lawyer_status_text": "Lawyer_status 0",
    "pay_status_text": "Pay_status 0",
    "pay_time_text": "",
    "order_status_text": null
    *
* */
