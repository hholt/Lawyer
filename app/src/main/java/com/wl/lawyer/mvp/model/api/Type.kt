package com.wl.lawyer.mvp.model.api

import androidx.annotation.StringDef

class Type {

    companion object {
        // 发送验证码
        const val REGISTER = "register"
        const val CHANGE_MOBILE = "changemobile"
        const val CHANGE_PWD = "changepwd"
        const val RESET_PWD = "resetpwd"

        // 账户类型
        const val MOBILE = "mobile"
        const val EMAIL = "other"
    }

    @StringDef(REGISTER, CHANGE_MOBILE, CHANGE_PWD, RESET_PWD)
    annotation class SendSmsEvent

    @StringDef(MOBILE, EMAIL)
    annotation class AccountType
}