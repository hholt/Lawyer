package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MyConsultOrderBean(
    val id: Int,
    @SerializedName("order_id") val orderId: String,
    @SerializedName("status") val status: String,
    @SerializedName("status_text") val statusText: String,
    @SerializedName("pay_time_text") val payTime_text: String,
    @SerializedName("pay_amount") val payAmount: String,
    @SerializedName("lawyer_id") val lawyerId: Int,
    @SerializedName("lawyer_name") val lawyerName: String,
    @SerializedName("lawyer_avatar") val lawyerAvatar: String,
    @SerializedName("package_name") val packageName: String,
    @SerializedName("package") val packageType: ConsultlationSetBean,
    @SerializedName("consultation_start_time") val consultationStartTime: Int,
    @SerializedName("consultation_end_time") val consultationEndTime: Int,
    @SerializedName("consultation_start_time_text") val startTimeText: String,
    @SerializedName("consultation_end_time_text") val endTimeText: String
) : Serializable

/*
*
*       "id": 26,
        "order_id": "CO20200513201044023880",
        "status": "paid",
        "status_text": "待接单",
        "pay_time": 1589371847,
        "pay_time_text": "2020-05-13 20:10:47",
        "lawyer_name": "于小伟",
        "lawyer_avatar": "/uploads/20200318/e794c4d1d49c6718585beb5efae2b685.jpg",
        "package_name": "图文咨询套餐",
        "consultation_start_time": 1589371844,
        "consultation_end_time": 0,
        "consultation_start_time_text": "2020.05.13 20:10",
        "consultation_end_time_text": "1970.01.01 08:00"
*
*
*  "id": 22,
    "order_id": "CO20200513114741672309",
    "status": "created",
    "status_text": "未支付",
    "pay_time": null,
    "pay_time_text": "",
    "pay_amount": "29.99",
    "lawyer_id": 0,
    "lawyer_name": "林海音",
    "lawyer_avatar": "/uploads/20200318/fd2452fae5329ccad6075021c850901b.jpg",
    "package": {
      "id": 1,
      "name": "图文咨询套餐",
      "voice_enable": "0",
      "video_enable": "0",
      "voice_enable_text": "不允许",
      "video_enable_text": "不允许",
      "status_text": "正常"
    },
    "consultation_start_time": 1589341661,
    "consultation_end_time": 1589348861,
    "consultation_start_time_text": "2020.05.13 11:47",
    "consultation_end_time_text": "2020.05.13 13:47"
* */