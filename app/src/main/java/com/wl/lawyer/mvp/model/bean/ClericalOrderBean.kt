package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ClericalOrderBean(
    @SerializedName("order_no") val orderNo: String,
    @SerializedName("user_id") val uId: Int,
    @SerializedName("user_memo") val memo: String,
    @SerializedName("specs") val specs: String,
    @SerializedName("refer_price") val referPrice: String,
    @SerializedName("earnest_price") val earnestPrice: String,
    @SerializedName("total_price") val totalPrice: String,
    @SerializedName("file_path") val filePath: String,
    @SerializedName("lawyer_id") val lawyerId: Int,
    @SerializedName("lawyer_status") val lawyerStatus: Int,
    @SerializedName("pay_status") val payStatus: Int,
    @SerializedName("createtime") val createtime: Long,
    @SerializedName("updatetime") val updatetime: Long,
    @SerializedName("id") val id: Int,
    @SerializedName("lawyer_status_text") val lawyerStatusText: String,
    @SerializedName("pay_status_text") val payStatusText: String,
    @SerializedName("pay_time_text") val payTimeText: String,
    @SerializedName("order_status_text") val orderStatusText: String?
) : Serializable

/*
*   "order_no": "EW20200512163731880387",
    "user_id": 27,
    "user_memo": "",
    "specs": "服务类型:文书处理,文书类型:文书A,协助方式:审查",
    "refer_price": "2.00",
    "earnest_price": "30.00",
    "total_price": "30.00",
    "file_path": "",
    "lawyer_id": "13",
    "lawyer_status": 0,
    "pay_status": 0,
    "createtime": 1589272651,
    "updatetime": 1589272651,
    "id": "28",
    "lawyer_status_text": "Lawyer_status 0",
    "pay_status_text": "Pay_status 0",
    "pay_time_text": "",
    "order_status_text": null
*
* */