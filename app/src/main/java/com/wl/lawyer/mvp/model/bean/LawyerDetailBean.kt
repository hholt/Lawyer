package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LawyerDetailBean(val id: Int, @SerializedName("group_id") val groupId: Int,
                            @SerializedName("nickname") val userName: String,
                            val avatar: String,
                            @SerializedName("lawyer_info")val lawyer: LawyerBean,
                            @SerializedName("lawyer_article")val articleList: List<LawyerArticleBean>,
                            val url: String): Serializable {
    /**
     * "id": 13,
     * "group_id": 2,
     * "nickname": "林海音",
     * "avatar": "/uploads/20200318/fd2452fae5329ccad6075021c850901b.jpg",
     *  "lawyer_info": {}
     *  "url": "/u/13"
     *
     */

}