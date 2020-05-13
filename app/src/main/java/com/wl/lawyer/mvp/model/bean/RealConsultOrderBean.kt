package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RealConsultOrderBean(
    @SerializedName("id") val id: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("order_type") val orderType: Int,
    @SerializedName("package_id") val setId: Int,
    @SerializedName("package_name") val name: String,
    @SerializedName("order_id") val orderId: String,
    @SerializedName("amount") val amount: String,
    @SerializedName("pay_amount") val payAmount: String,
    @SerializedName("pay_type") val payType: String,
    @SerializedName("pay_time") val payTime: Long,
    @SerializedName("status") val status: String,
    @SerializedName("remark") val remark: String,
    @SerializedName("invite_lawyer_id") val lawyerId: Int,
    @SerializedName("invite_count") val inviteCount: Int,
    @SerializedName("invite_status") val inviteStatus: String,
    @SerializedName("invite_start_time") val startTime: Long,
    @SerializedName("service_lawyer_id") val serviceLawyerId: Int,
    @SerializedName("service_lawyer_receipt_time") val receiptTime: Long,
    @SerializedName("service_lawyer_end_time") val endTime: Long,
    @SerializedName("createtime") val createTime: Long,
    @SerializedName("updatetime") val updateTime: Long,
    @SerializedName("order_status") val orderStatus: Int,
    @SerializedName("pay_time_text") val payText: String,
    @SerializedName("status_text") val statusText: String
) : Serializable

/*
*
*     "id": 23,
      "user_id": 27,
      "order_type": 1,
      "package_id": 1,
      "package_name": "图文咨询套餐",
      "order_id": "CO20200513172353543987",
      "amount": "29.99",
      "pay_amount": "29.99",
      "pay_type": "money",
      "pay_time": 1589362735,
      "ip": "61.141.73.90",
      "user_agent": "okhttp/3.12.3",
      "remark": "在线咨询",
      "createtime": 1589361833,
      "updatetime": 1589362735,
      "status": "paid",
      "transaction_id": null,
      "invite_lawyer_id": 15,
      "invite_status": "inviting",
      "invite_start_time": 1589361833,
      "invite_count": 1,
      "service_lawyer_id": 0,
      "service_lawyer_receipt_time": 1589361833,
      "service_lawyer_end_time": 0,
      "order_status": 0,
      "pay_time_text": "2020-05-13 17:38:55",
      "status_text": "待接单"
*
* */