package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserBean(@SerializedName("userinfo") var userinfo: UserinfoBean? = null) : Serializable {

    data class UserinfoBean(
        var id: Int = 0,
        var username: String? = null,
        var nickname: String? = null,
        var mobile: String? = null,
        var avatar: String? = null,
        var bio: String? = null,
        var score: Int = 0,
        var token: String? = null,
        @SerializedName("user_id") var userId: Int = 0,
        var createtime: Long = 0,
        var expiretime: Long = 0,
        @SerializedName("expires_in") var expiresIn: Long = 0,
        var gender: Int = 0,
        var address: String? = null
    ) : Serializable


    /*
    *        "id": 27,
             "username": "18627794119",
             "nickname": "18627794119",
             "mobile": "18627794119",
             "avatar": "\/assets\/img\/default_avatar.png",
             "bio": "这个人很懒什么都没有留下",
             "score": 0,
             "token": "154b9024-0491-4796-8635-c91ada778410",
             "user_id": 27,
             "createtime": 1588988106,
             "expiretime": 1591580106,
             "expires_in": 2592000
    * */

}
