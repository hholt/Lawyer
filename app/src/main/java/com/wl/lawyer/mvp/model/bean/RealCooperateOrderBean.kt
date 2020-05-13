package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RealCooperateOrderBean(
    val id: Int,
    @SerializedName("order_no") val orderNo: String,
    @SerializedName("order_type") val orderType: Int,
    @SerializedName("user_id") val uId: Int,
    @SerializedName("service_id") val serviceId: Int,
    @SerializedName("service_name") val serviceName: String,
    @SerializedName("service_content") val serviceContent: String,
    @SerializedName("service_price") val servicePrice: String,
    @SerializedName("other_price") val otherPrice: String,
    @SerializedName("total_price") val totalPrice: String,
    @SerializedName("real_pay_price") val realPayPrice: String,
    @SerializedName("lawyer_id") val lawyerId: Int,
    @SerializedName("lawyer_status") val lawyerStatus: Int,
    @SerializedName("pay_way") val payWay: String,
    @SerializedName("pay_status") val payStatus: Int,
    @SerializedName("pay_time") val payTime: Long,
    @SerializedName("createtime") val createtime: Long,
    @SerializedName("updatetime") val updatetime: Long,
    @SerializedName("deletetime") val deletetime: Any,
    @SerializedName("service_lawyer_receipt_time") val receiptTime: Any,
    @SerializedName("service_lawyer_end_time") val endTime: Any,
    @SerializedName("order_status") val orderStatus: Int,
    @SerializedName("lawyer_status_text") val lawyerStatusText: String,
    @SerializedName("pay_status_text") val payStatusText: String,
    @SerializedName("pay_time_text") val payTimeText: String,
    @SerializedName("order_status_text") val order_status_text: String?
) : Serializable
/*
*
*     "id": 15,
      "order_no": "LC20200513155745799322",
      "order_type": 3,
      "pay_order_no": "",
      "transaction_id": "",
      "user_id": 27,
      "service_id": 1,
      "service_name": "律师陪同谈判、纠纷处理",
      "service_content": "",
      "service_price": "2000.00",
      "other_price": "300.00",
      "total_price": "2300.00",
      "real_pay_price": "2300.00",
      "lawyer_id": 13,
      "lawyer_status": 0,
      "pay_way": "money",
      "pay_status": 1,
      "pay_time": 1589374168,
      "createtime": 1589356665,
      "updatetime": 1589374168,
      "deletetime": null,
      "service_lawyer_receipt_time": null,
      "service_lawyer_end_time": 0,
      "order_status": 0,
      "lawyer_status_text": "Lawyer_status 0",
      "pay_status_text": "Pay_status 1",
      "pay_time_text": "2020-05-13 20:49:28",
      "order_status_text": "待接单"
*
*
* */