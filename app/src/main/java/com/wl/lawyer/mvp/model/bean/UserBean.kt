package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserBean : Serializable {

    var userinfo: UserinfoBean? = null

    class UserinfoBean : Serializable {
        var id: Int = 0
        var username: String? = null
        var nickname: String? = null
        var mobile: String? = null
        var avatar: String? = null
        var bio: String? = null
        var score: Int = 0
        var token: String? = null
        @SerializedName("user_id")
        var userId: Int = 0
        var createtime: Int = 0
        var expiretime: Int = 0
        @SerializedName("expires_in")
        var expiresIn: Int = 0
        var gender: Int = 0
        var address: String? = null

        override fun toString(): String {
            return "UserinfoBean(id=$id, username=$username, nickname=$nickname, mobile=$mobile, " +
                    "avatar=$avatar, bio=$bio, score=$score, token=$token, userId=$userId, " +
                    "createtime=$createtime, expiretime=$expiretime, expiresIn=$expiresIn, " +
                    "gender=$gender, address=$address)"
        }

    }

}
