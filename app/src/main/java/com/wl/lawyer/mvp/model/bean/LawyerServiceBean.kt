package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LawyerServiceBean(val id: Int, val name: String, val desc: String,
                             @SerializedName("icon_image") val iconImage: String,
                             val price: Float,
                             @SerializedName("status_text") val statusText: String,
                             val isHeader: Boolean,
                             val header: String): Serializable
/**
 * id : 1
 * name : 在线咨询
 * desc : 多种咨询方式满足您的所有需求
 * icon_image : /uploads/20200213/22ab77fca36fca265e6cd6998ba1f862.png
 * price : 29.99
 * status_text :
 */