package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LawyerArticleBean(val id: Int, val title: String, val image: String, val desc: String,
                             @SerializedName("createtime") val createTime: Long,
                             @SerializedName("status_text") val statusText: String) : Serializable
    /*
    *
    * "id": 43,
    * "title": "法在心中",
    * "image": "/uploads/20200425/0b5f394f4c84b97aa4ec9758c2edb030.jpg",
    * "desc": " “法律，是由立法机关制定、颁布的各种强制行为规则的总称。”这是《新华词典》对法律的解释。但这种解释对年少的我来说太模糊，太抽象了。法律，它到底是什么？为什么这样至高无上，人人都要遵守？",
    * "createtime": 1580808426,
    * "status_text": ""
    *
    * */

