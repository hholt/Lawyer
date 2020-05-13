package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ConsultOrderBean(@SerializedName("user_id") val userId: Int,
                            @SerializedName("package_id") val setId: Int,
                            @SerializedName("package_name") val name: String,
                            @SerializedName("order_id") val orderId: String,
                            @SerializedName("amount") val amount: String,
                            @SerializedName("status") val status: String,
                            @SerializedName("remark") val remark: String,
                            @SerializedName("pay_amount") val payAmount: String,
                            @SerializedName("service_lawyer_receipt_time") val receiptTime: Long,
                            @SerializedName("invite_lawyer_id") val lawyerId: Int,
                            @SerializedName("invite_count") val inviteCount: Int,
                            @SerializedName("invite_status") val inviteStatus: String,
                            @SerializedName("invite_start_time") val startTime: Long,
                            @SerializedName("createtime") val createTime: Long,
                            @SerializedName("updatetime") val updateTime: Long,
                            @SerializedName("id") val id: Int,
                            @SerializedName("pay_time_text") val payText: String,
                            @SerializedName("status_text") val statusText: String
                           ): Serializable {
    /*
    "user_id": 27,
    "package_id": "1",
    "package_name": "图文咨询套餐",
    "order_id": "CO20200504151708698588",
    "amount": "29.99",
    "ip": "61.141.73.39",
    "user_agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.129 Safari/537.36",
    "status": "created",
    "remark": "在线咨询",
    "pay_amount": "29.99",
    "service_lawyer_receipt_time": 1588576628,
    "invite_lawyer_id": 13,
    "invite_count": 1,
    "invite_status": "inviting",
    "invite_start_time": 1588576628,
    "createtime": 1588576628,
    "updatetime": 1588576628,
    "id": "13",
    "pay_time_text": "",
    "status_text": "未支付"
    */
}