package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GraphicConsultationBean(
    val id: Int,
    @SerializedName("user_id") val uid: Int,
    val title: String,
    val content: String,
    @SerializedName("p_t_category_id") val categoryId: Int,
    val images: String,
    val status: Int,
    @SerializedName("createtime") val createTime: Long,
    @SerializedName("updatetime") val updateTime: Long,
    @SerializedName("view_count") val viewCount: Int,
    val comments: List<ConsulationCommentBean>,
    @SerializedName("p_t_category_name") val categoryName: String,
    val cover: Any,
    @SerializedName("lawyer_comment_count") val lawyerCommentCount: Int
) : Serializable
/*
*
    "id": 21,
    "user_id": 27,
    "title": "寻求意见",
    "content": "寻求意见寻求意见寻求意见寻求意见寻求意见寻求意见寻求意见",
    "p_t_category_id": 1,
    "images": "",
    "status": "1",
    "createtime": 1588844050,
    "updatetime": 1588844050,
    "deletetime": null,
    "view_count": 0,
    "comment_count": 0,
    "comments": [],
    "p_t_category_name": "民事纠纷",
    "cover": null,
    "lawyer_comment_count": 0

*
* */