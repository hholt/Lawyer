package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ConsulationCommentBean(
    val id: Int,
    @SerializedName("user_id") val uid: Int,
    val content: String,
    @SerializedName("to_comment_id") val toId: Int,
    val createtime: Long,
    @SerializedName("to_comment_count") val toCommentCount: Int,
    @SerializedName("comment_count") val commentCount: Int,
    val children: List<ConsulationCommentBean>,
    val user: CommentUserBean,
    @SerializedName("to_user") val toUser: CommentUserBean?
) : Serializable {
    /*
    *
    *       "id": 59,
            "user_id": 23,
            "content": "q",
            "to_comment_id": 0,
            "createtime": 1588855943,
            "to_comment_count": 6,
            "comment_count": 21,
    *
    * */
    /*data class ChildConsulationCommentBean(
        val id: Int,
        @SerializedName("user_id") val uid: Int,
        val content: String,
        @SerializedName("to_comment_id") val toId: Int,
        val createtime: Long,
        @SerializedName("to_comment_count") val toCommentCount: Int,
        @SerializedName("comment_count") val commontCount: Int,
        val user: CommentUserBean,
        @SerializedName("to_user") val toUser: CommentUserBean
    ) : Serializable

    *//*"id": 70,
    "user_id": 23,
    "content": "hdhhd",
    "to_comment_id": 66,
    "createtime": 1588856979,
    "to_comment_count": 0,
    "user": {
        "id": 23,
        "nickname": "yunwee",
        "avatar": "/uploads/20200507/fd611f33e3b27cf6192552cf8447aded.jpg",
        "group_id": 2,
        "url": "/u/23"
    },
    "to_user": {
        "id": 23,
        "nickname": "yunwee",
        "avatar": "/uploads/20200507/fd611f33e3b27cf6192552cf8447aded.jpg",
        "group_id": 2,
        "url": "/u/23"
        }*/

    data class CommentUserBean(
        val id: Int,
        val nickname: String,
        val avatar: String,
        @SerializedName("group_id") val groupId: Int,
        val url: String
    ) : Serializable

    /*
    *
    *         "id": 23,
              "nickname": "yunwee",
              "avatar": "/uploads/20200507/fd611f33e3b27cf6192552cf8447aded.jpg",
              "group_id": 2,
              "url": "/u/23"
    * */
}
