package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ConsultlationSetBean(val id: Int, val name: String, val price: Float, val desc: String,
                                @SerializedName("talk_count")val talkCount: Int,
                                @SerializedName("duration_hour") val durationHour: Int,
                                @SerializedName("voice_enable") val voiceEnable: String,
                                @SerializedName("video_enable") val videoEnable: String,
                                @SerializedName("sell_count") val sellCount: Int,
                                val status: String,
                                @SerializedName("createtime") val createtime: Long,
                                @SerializedName("updatetime") val updatetime: Long,
                                @SerializedName("voice_enable_text") val voiceEnableText: String,
                                @SerializedName("video_enable_text") val videoEnableText: String,
                                @SerializedName("status_text") val statusText: String
) : Serializable
/*"id": 1,
"name": "图文咨询套餐",
"price": "29.99",
"desc": "1. 允许1小时内1000次律师回复\r\n2. 允许发送图片，文字消息内容",
"talk_count": 1000,
"duration_hour": 2,
"voice_enable": "0",
"video_enable": "0",
"sell_count": 0,
"status": "1",
"createtime": 1581305454,
"updatetime": 1581305751,
"voice_enable_text": "不允许",
"video_enable_text": "不允许",
"status_text": "正常"*/